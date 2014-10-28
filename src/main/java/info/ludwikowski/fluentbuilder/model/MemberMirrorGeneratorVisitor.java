/*
 * Created on 03-04-2013 18:36:02 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.model.ImportsFactory.createNecessaryImportsForTypeInClass;
import static info.ludwikowski.fluentbuilder.model.MemberMirrorImpl.collectionMirror;
import static info.ludwikowski.fluentbuilder.model.MemberMirrorImpl.simpleMirror;
import static info.ludwikowski.fluentbuilder.util.NameUtils.removePackageNameFromFullyQualifiedName;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

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

/**
 * A visitor for creating MemberMirrors by visiting Java elements.
 *
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public class MemberMirrorGeneratorVisitor extends SimpleTypeVisitor6<MemberMirror, Element> {

	@Override
	/**
	 * Creates a simple {@link MemberMirror} for a {@link Element} which is of a {@link PrimitiveType}.
	 * This includes fields which are of a primitive type.
	 * @return MemberMirror representation of the primitive field
	 */
	public final MemberMirror visitPrimitive(final PrimitiveType primitiveType, final Element element) {
		return simpleTypes(primitiveType, element);
	}

	@SuppressWarnings("unchecked")
	private MemberMirror simpleTypes(final TypeMirror primitiveType, final Element element) {

		final String name = element.toString();
		final String simpleType = removePackageNameFromFullyQualifiedName(primitiveType.toString());
		return MemberMirrorImpl.simpleMirror(name, simpleType, Collections.EMPTY_SET);
	}

	@Override
	/**
	 * Creates a simple {@link MemberMirror} for a {@link Element} which is of a {@link ArrayType}.
	 * This include fields which are Arrays.
	 * @return MemberMirror representation of the array field
	 */
	public final MemberMirror visitArray(final ArrayType t, final Element element) {
		return simpleTypes(t, element);
	}

	@Override
	/**
	 * Creates a {@link MemberMirror} for a {@link Element} which is of a {@link DeclaredType}.
	 * This include fields which are not of a primitive type.
	 * @return MemberMirror representation of the declared field
	 */
	public final MemberMirror visitDeclared(final DeclaredType declaredType, final Element element) {

		final TypeElement returnedElement = (TypeElement) declaredType.asElement();

		final String name = element.toString();
		final String simpleType = removePackageNameFromFullyQualifiedName(declaredType.toString());
		final Set<String> imports = getImports(declaredType, returnedElement);
		final String type = returnedElement.toString();

		if (TypeUtils.isListOrSet(type) && isGeneric(declaredType)) {

			return collectionMirror(
					name,
					simpleType,
					imports,
					type,
					collectionElementSimpleName(declaredType));
		}

		return simpleMirror(name, simpleType, imports);
	}

	private boolean isGeneric(final DeclaredType declaredType) {
		return !declaredType.getTypeArguments().isEmpty();
	}

	private String collectionElementSimpleName(final DeclaredType declaredType) {
		return removePackageNameFromFullyQualifiedName(declaredType.getTypeArguments().get(0).toString());
	}

	private Set<String> getImports(final DeclaredType declaredType, final TypeElement returnedElement) {

		final Set<String> imports = new TreeSet<String>();
		final String type = returnedElement.toString();

		imports.addAll(createNecessaryImportsForTypeInClass(type));

		for (final TypeMirror typeMirror : declaredType.getTypeArguments()) {
			imports.addAll(ImportsFactory.onlyImports(typeMirror.toString()));
		}
		return imports;
	}
}
