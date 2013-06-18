/*
 * Created on 03-04-2013 18:36:02 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import static info.ludwikowski.model.ImportsHelper.createNecessaryImports;
import static info.ludwikowski.model.ImportsHelper.onlyImports;
import static info.ludwikowski.util.TypeUtils.isListOrSet;
import static info.ludwikowski.util.TypeUtils.simpleType;
import info.ludwikowski.processor.ProcessorContext;
import info.ludwikowski.util.StringUtils;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor6;

public class MemberMirrorGeneratorVisitor extends SimpleTypeVisitor6<MemberMirror, Element> {

	private final ProcessorContext context;


	public MemberMirrorGeneratorVisitor(ProcessorContext context) {
		this.context = context;
	}

	@Override
	public MemberMirror visitPrimitive(PrimitiveType primitiveType, Element element) {

		return simpleTypes(primitiveType, element);
	}

	@SuppressWarnings("unchecked")
	private MemberMirror simpleTypes(TypeMirror primitiveType, Element element) {

		String name = element.toString();
		String simpleType = simpleType(primitiveType.toString());

		return MemberMirrorImpl.simpleMirror(name,
				simpleType,
				Collections.EMPTY_SET);
	}

	@Override
	public MemberMirror visitArray(ArrayType t, Element element) {
		return simpleTypes(t, element);
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

		imports.addAll(createNecessaryImports(type));

		for (TypeMirror typeMirror : declaredType.getTypeArguments()) {

			imports.addAll(onlyImports(typeMirror.toString()));
		}
		return imports;
	}
}
