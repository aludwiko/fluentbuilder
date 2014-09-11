/*
 * Created on 09-03-2013 22:27:28 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */

package info.ludwikowski.fluentbuilder.model;

import info.ludwikowski.fluentbuilder.common.Context;
import info.ludwikowski.fluentbuilder.processor.ProcessorContext;
import info.ludwikowski.fluentbuilder.util.StringUtils;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

/**
 * Implements the ClassMirror interface. A ClassMirror represents a given class
 * and its member fields.
 * 
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public class ClassMirrorImpl implements ClassMirror {

	private static final Logger LOGGER = Logger.getLogger(ClassMirrorImpl.class.getName());

	private final String simpleName;
	private final String packageName;
	private final List<MemberMirror> members = new LinkedList<MemberMirror>();
	private final List<Constructor> constructors = new LinkedList<Constructor>();


	/**
	 * Creates a ClassMirrorImpl from a given element which represents a class.
	 * This is needed because the APT processes classes as elements.
	 * 
	 * @param element - a class which is parsed to an element by the APT
	 * @param context - configuration for mirror creation and code generation
	 */
	public ClassMirrorImpl(final Element element, final ProcessorContext context) {
		final TypeElement typeElement = (TypeElement) element;
		simpleName = typeElement.getSimpleName().toString();
		packageName = typeElement.getQualifiedName().toString().replace("." + simpleName, StringUtils.EMPTY);
		fillMemberMirrors(element, context);
	}

	/**
	 * Creates a ClassMirrorImpl from a given class with a given configuration.
	 * 
	 * @param clazz - class which will be mirrored
	 * @param context - configuration for mirror creation and code generation
	 */
	public ClassMirrorImpl(final Class<?> clazz, final Context context) {

		simpleName = clazz.getSimpleName();
		packageName = clazz.getCanonicalName().replace("." + simpleName, StringUtils.EMPTY);
		fillMemberMirrors(clazz, context);
		fillConstructors(clazz);
	}

	private void fillConstructors(Class<?> clazz) {
		for (java.lang.reflect.Constructor<?> constructor : clazz.getConstructors()) {
			constructors.add(Constructor.create(constructor));
		}
	}

	private void fillMemberMirrors(final Class<?> clazz, final Context context) {
		final List<Field> properFields = properFields(clazz);
		for (final Field field : properFields) {
			members.add(MemberMirrorCreator.create(field, context));
		}
		fillSuperMemberMirrors(clazz, context);
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

	private void fillMemberMirrors(final Element element, final ProcessorContext context) {

		final List<? extends Element> fieldsOfClass = ElementFilter.fieldsIn(element.getEnclosedElements());
		visitFieldsList(fieldsOfClass, context);

		final List<? extends ExecutableElement> constructorsOfClass = ElementFilter.constructorsIn(element
																											.getEnclosedElements());
		visitConstructorList(constructorsOfClass, context);

		try {
			final Class<?> baseClass = Class.forName(element.toString());
			fillSuperMemberMirrors(baseClass, context);
		}
		catch (ClassNotFoundException e) {
			LOGGER.severe("Could not find class: " + element.toString() + " No members added!");
		}
	}

	private void visitFieldsList(final List<? extends Element> fieldsOfClass, final ProcessorContext context) {
		for (final Element field : fieldsOfClass) {
			if (TypeUtils.isStaticOrFinal(field)) {
				continue;
			}
			visitField(field, context);
		}
	}

	private void visitField(final Element field, final ProcessorContext context) {
		final MemberMirrorGeneratorVisitor visitor = new MemberMirrorGeneratorVisitor(context);
		final MemberMirror member = field.asType().accept(visitor, field);
		if (member != null) {
			members.add(member);
		}
	}

	private void visitConstructorList(final List<? extends ExecutableElement> constructorsOfClass,
			final ProcessorContext context) {
		for (ExecutableElement constructor : constructorsOfClass) {
			final MemberMirrorGeneratorVisitor visitor = new MemberMirrorGeneratorVisitor(context);
			final MemberMirror member = constructor.asType().accept(visitor, constructor);
			if (member != null) {
				members.add(member);
			}
		}
	}

	private void fillSuperMemberMirrors(final Class<?> clazz, final Context context) {
		final Class<?> superClass = clazz.getSuperclass();
		if (clazz.getSuperclass() != null && !"Object".equals(superClass.getName())) {
			fillMemberMirrors(superClass, context);
		}
	}

	@Override
	public final List<MemberMirror> getMembers() {
		return members;
	}

	@Override
	public final List<Constructor> getConstructors() {
		return constructors;
	}

	/**
	 * This method returns all MemberMirrors which represent a class field.
	 * 
	 * @return all class fields as a list of MemberMirrorImpl
	 */
	@Override
	public final List<MemberMirrorImpl> getFieldMembers() {
		final List<MemberMirrorImpl> fieldMembers = new ArrayList<MemberMirrorImpl>();
		for (MemberMirror memberMirror : members) {
			if (memberMirror instanceof MemberMirrorImpl) {
				fieldMembers.add((MemberMirrorImpl) memberMirror);
			}
		}
		return fieldMembers;
	}

	@Override
	public final String getPackageName() {
		return packageName;
	}

	@Override
	public final Set<String> getImports() {

		final Imports imports = new Imports();

		for (final MemberMirror member : members) {
			imports.addAll(member.getImports());
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
