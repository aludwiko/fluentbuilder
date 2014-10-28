/*
 * Created on 09-03-2013 22:27:28 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */

package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.util.TypeUtils.isStaticOrFinal;
import info.ludwikowski.fluentbuilder.util.StringUtils;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

/**
 * Implements the ClassMirror interface. A ClassMirror represents a given class
 * and its member fields.
 *
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public class ClassMirrorImpl implements ClassMirror {

	private final String simpleName;
	private final String packageName;
	private final List<MemberMirror> members = new LinkedList<MemberMirror>();
	private final Set<Constructor> constructors = new TreeSet<Constructor>();
	private List<String> ignoredFieldRegexps = new LinkedList<String>();


	/**
	 * Creates a ClassMirrorImpl from a given element which represents a class.
	 * This is needed because the APT processes classes as elements.
	 * 
	 * @param element - a class which is parsed to an element by the APT
	 * @param ignoredFieldRegexps list with regexpes of fields that should be ignored. might be empty
	 */
	public ClassMirrorImpl(final Element element, List<String> ignoredFieldRegexps) {
		final TypeElement typeElement = (TypeElement) element;
		simpleName = typeElement.getSimpleName().toString();
		packageName = typeElement.getQualifiedName().toString().replace("." + simpleName, StringUtils.EMPTY);
		this.ignoredFieldRegexps = ignoredFieldRegexps;
		fillMemberMirrors(element);
		fillConstructors(element);
	}

	/**
	 * Creates a ClassMirrorImpl from a given class with a given configuration.
	 *
	 * @param clazz - class which will be mirrored
	 */
	public ClassMirrorImpl(final Class<?> clazz, List<String> ignoredFieldRegexps) {

		simpleName = clazz.getSimpleName();
		packageName = clazz.getCanonicalName().replace("." + simpleName, StringUtils.EMPTY);
		this.ignoredFieldRegexps = ignoredFieldRegexps;
		fillMemberMirrors(clazz);
		fillConstructors(clazz);
	}

	private void fillConstructors(Class<?> clazz) {

		for (java.lang.reflect.Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			constructors.add(Constructor.create(constructor));
		}
	}

	private void fillMemberMirrors(final Class<?> clazz) {

		final List<Field> properFields = properFields(clazz);
		for (final Field field : properFields) {
			members.add(MemberMirrorCreator.create(field));
		}

		final Class<?> superClass = clazz.getSuperclass();
		if (isNotObjectClass(superClass)) {
			// filling inheritted fields
			fillMemberMirrors(superClass);
		}
	}

	private boolean isNotObjectClass(final Class<?> superClass) {
		return superClass != null && !superClass.isAssignableFrom(Object.class);
	}

	private List<Field> properFields(final Class<?> clazz) {

		final List<Field> properFields = new LinkedList<Field>();

		for (final Field field : clazz.getDeclaredFields()) {

			if (!TypeUtils.isStaticOrFinal(field)) {

				properFields.add(field);
			}
		}
		return properFields;
	}

	private void fillMemberMirrors(final Element element) {

		final List<? extends Element> fieldsOfClass = ElementFilter.fieldsIn(element.getEnclosedElements());
		visitFieldsList(fieldsOfClass);

		if (hasParent(element)) {
			fillMemberMirrors(parentType(element).asElement());
		}
	}

	private boolean hasParent(final Element element) {
		final DeclaredType parentType = parentType(element);
		return !parentType.toString().equals(Object.class.getCanonicalName());
	}

	private DeclaredType parentType(final Element element) {
		final TypeMirror parent = ((TypeElement) element).getSuperclass();
		final DeclaredType parentType = (DeclaredType) parent;
		return parentType;
	}

	private void visitFieldsList(final List<? extends Element> fieldsOfClass) {
		for (final Element field : fieldsOfClass) {
			if (isStaticOrFinal(field)) {
				continue;
			}
			visitField(field);
		}
	}

	private void visitField(final Element field) {
		final MemberMirrorGeneratorVisitor visitor = new MemberMirrorGeneratorVisitor();
		final MemberMirror member = field.asType().accept(visitor, field);
		if (member != null) {
			members.add(member);
		}
	}

	private void fillConstructors(Element element) {
		final List<ExecutableElement> constructorsOfClass = ElementFilter.constructorsIn(element.getEnclosedElements());
		visitConstructorList(constructorsOfClass);
	}

	private void visitConstructorList(final List<ExecutableElement> constructorsOfClass) {
		for (ExecutableElement constructorOfClass : constructorsOfClass) {
			final ConstructorGeneratorVisitor visitor = new ConstructorGeneratorVisitor();
			constructors.add(constructorOfClass.asType().accept(visitor, constructorOfClass));
		}
	}


	@Override
	public final List<MemberMirror> getMembers() {
		return filterMembers();
	}

	private List<MemberMirror> filterMembers() {

		List<MemberMirror> filtered = new ArrayList<MemberMirror>();
		for (MemberMirror member : members) {
			if (notIgnore(member)) {
				filtered.add(member);
			}
		}
		return filtered;
	}

	private boolean notIgnore(MemberMirror memberMirror) {

		if (ignoredFieldRegexps == null) {
			return true;
		}

		for (String regexp : ignoredFieldRegexps) {
			if (memberMirror.getName().matches(regexp)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public final Collection<Constructor> getConstructors() {
		return constructors;
	}

	@Override
	public final String getPackageName() {
		return packageName;
	}

	@Override
	public final Set<String> getImports() {

		final Imports imports = new Imports();

		for (final MemberMirror member : getMembers()) {
			imports.addAll(member.getImports());
		}

		return imports.asSet();
	}

	@Override
	public final Set<String> getOnlyConstructorsImports() {

		final Imports imports = new Imports();

		for (final Constructor constructor : constructors) {
			imports.addAll(constructor.getParamsTypes());
		}

		return imports.asSet();
	}

	@Override
	public final String getSimpleName() {
		return simpleName;
	}

	@Override
	public final List<MemberMirror> getVarArgsMembers() {

		final List<MemberMirror> varArgsMembers = new LinkedList<MemberMirror>();

		for (final MemberMirror member : members) {
			if (member.isSupportedVarArgsCollection()) {
				varArgsMembers.add(member);
			}
		}

		return varArgsMembers;
	}

}
