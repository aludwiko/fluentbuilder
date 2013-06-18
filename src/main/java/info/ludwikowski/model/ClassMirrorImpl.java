/*
 * Created on 09-03-2013 22:27:28 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import info.ludwikowski.common.Context;
import info.ludwikowski.processor.ProcessorContext;
import info.ludwikowski.util.StringUtils;
import info.ludwikowski.util.TypeUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

public class ClassMirrorImpl implements ClassMirror {

	private String simpleName;
	private String packageName;
	private List<MemberMirror> members = new LinkedList<MemberMirror>();


	public ClassMirrorImpl(Element element, ProcessorContext context) {

		TypeElement typeElement = (TypeElement) element;
		simpleName = typeElement.getSimpleName().toString();
		packageName = typeElement.getQualifiedName().toString().replace("." + simpleName, StringUtils.EMPTY);
		fillMemberMirrors(element, context);
	}

	public ClassMirrorImpl(Class<?> clazz, Context context) {

		simpleName = clazz.getSimpleName();
		packageName = clazz.getCanonicalName().replace("." + simpleName, StringUtils.EMPTY);

		fillMemberMirrors(clazz, context);
	}

	private void fillMemberMirrors(Class<?> clazz, Context context) {

		List<Field> properFileds = properFileds(clazz);

		for (Field field : properFileds) {

			members.add(MemberMirrorCreator.create(field, context));
		}
	}

	private List<Field> properFileds(Class<?> clazz) {

		List<Field> properFields = new LinkedList<Field>();

		for (Field field : clazz.getDeclaredFields()) {

			if (!TypeUtils.isStaticOrFinal(field)) {

				properFields.add(field);
			}
		}
		return properFields;
	}

	private void fillMemberMirrors(Element element, ProcessorContext context) {

		List<? extends Element> fieldsOfClass = ElementFilter.fieldsIn(element.getEnclosedElements());

		for (Element field : fieldsOfClass) {

			if (TypeUtils.isStaticOrFinal(field)) {
				continue;
			}

			MemberMirrorGeneratorVisitor v = new MemberMirrorGeneratorVisitor(context);
			MemberMirror member = field.asType().accept(v, field);
			if (member != null) {
				members.add(member);
			}
		}
	}

	@Override
	public List<MemberMirror> getMembers() {
		return members;
	}

	@Override
	public String getPackageName() {
		return packageName;
	}

	@Override
	public Set<String> getImports() {

		Imports imports = new Imports();

		for (MemberMirror member : members) {
			imports.addAll(member.getImports());
		}

		return imports.asSet();
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public List<MemberMirror> getVarArgsMembers() {

		List<MemberMirror> varargsMembers = new LinkedList<MemberMirror>();

		for (MemberMirror member : members) {
			if (member.isSupporterVarargsCollection()) {
				varargsMembers.add(member);
			}
		}

		return varargsMembers;
	}

}
