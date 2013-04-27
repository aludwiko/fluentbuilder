/*
 * Created on 03-04-2013 18:36:02 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import static info.ludwikowski.util.TypeUtils.isList;
import static info.ludwikowski.util.TypeUtils.isListOrSet;
import static info.ludwikowski.util.TypeUtils.simpleType;
import info.ludwikowski.processor.ProcessorContext;
import info.ludwikowski.util.StringUtils;
import info.ludwikowski.util.TypeUtils;

import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.SimpleTypeVisitor6;

public class MemberMirrorGeneratorVisitor extends SimpleTypeVisitor6<MemberMirror, Element> {

	private final ProcessorContext context;


	public MemberMirrorGeneratorVisitor(ProcessorContext context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public MemberMirror visitTypeVariable(TypeVariable declaredType, Element p) {
		// TODO Auto-generated method stub
		return super.visitTypeVariable(declaredType, p);
	}

	@Override
	public MemberMirror visitDeclared(DeclaredType declaredType, Element element) {

		TypeElement returnedElement = (TypeElement) context.getTypeUtils().asElement(declaredType);

		String name = element.toString();
		String simpleType = simpleType(declaredType.toString());
		Set<String> imports = imports(declaredType, returnedElement);
		String type = returnedElement.toString();

		if (isListOrSet(type) && isGeneric(declaredType)) {

			return MemberMirrorImpl.collectionMirror(name,
					simpleType,
					imports,
					type,
					collectionElementSimpleName(declaredType));
		}

		return MemberMirrorImpl.simpleMirror(name,
				simpleType,
				imports);
	}

	private boolean isGeneric(DeclaredType declaredType) {
		return !declaredType.getTypeArguments().isEmpty();
	}

	private String collectionElementSimpleName(DeclaredType declaredType) {

		return StringUtils.removePackage(declaredType.getTypeArguments().get(0).toString());
	}

	private Set<String> imports(DeclaredType declaredType, TypeElement returnedElement) {

		Set<String> imports = new TreeSet<String>();

		String type = returnedElement.toString();

		if (isListOrSet(returnedElement.toString())) {
			if (isList(type)) {
				imports.add("java.util.ArrayList");
				imports.add("java.util.List");
			}
			else if (TypeUtils.isSet(type)) {
				imports.add("java.util.HashSet");
				imports.add("java.util.Set");
			}
			imports.add("java.util.Arrays");
		}
		else {
			imports.add(returnedElement.toString());
		}

		for (TypeMirror typeMirror : declaredType.getTypeArguments()) {

			imports.addAll(StringUtils.onlyImports(typeMirror.toString()));
		}
		return imports;
	}
}
