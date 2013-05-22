/*
 * Created on 09-03-2013 22:27:28 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import info.ludwikowski.processor.ProcessorContext;
import info.ludwikowski.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

public class ClassMirrorImpl implements ClassMirror {

	private String simpleName;
	private String packageName;
	private Set<String> imports = new TreeSet<String>();
	private List<MemberMirror> members = new LinkedList<MemberMirror>();


	public ClassMirrorImpl(Element element, ProcessorContext context) {

		TypeElement typeElement = (TypeElement) element;
		simpleName = typeElement.getSimpleName().toString();
		packageName = typeElement.getQualifiedName().toString().replace("." + simpleName, StringUtils.EMPTY);
		fillMemberMirrors(element, context);
	}

	private void fillMemberMirrors(Element element, ProcessorContext context) {

		List<? extends Element> fieldsOfClass = ElementFilter.fieldsIn(element.getEnclosedElements());

		for (Element field : fieldsOfClass) {

			if (isStatic(field) || isFinal(field)) {
				continue;
			}

			MemberMirrorGeneratorVisitor v = new MemberMirrorGeneratorVisitor(context);
			MemberMirror member = field.asType().accept(v, field);
			if (member != null) {
				members.add(member);
			}
		}
	}

	private boolean isFinal(Element field) {
		return field.getModifiers().contains(Modifier.FINAL);
	}

	private boolean isStatic(Element field) {
		return field.getModifiers().contains(Modifier.STATIC);
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

		for (MemberMirror member : members) {
			imports.addAll(member.getImports());
		}

		return imports;
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
