/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.model.ImportsFactory.createNecessaryImportsForTypeInClass;
import static info.ludwikowski.fluentbuilder.model.ImportsFactory.onlyImports;
import static info.ludwikowski.fluentbuilder.model.MemberMirrorImpl.collectionMirror;
import static info.ludwikowski.fluentbuilder.model.MemberMirrorImpl.simpleMirror;
import static info.ludwikowski.fluentbuilder.util.NameUtils.removePackageNameFromFullyQualifiedName;
import static info.ludwikowski.fluentbuilder.util.TypeUtils.isGeneric;
import static info.ludwikowski.fluentbuilder.util.TypeUtils.isListOrSet;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class creates MemberMirrors by given fields.
 * 
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public final class MemberMirrorCreator {

	private MemberMirrorCreator() {

	}

	/**
	 * Creates a MemberMirror instance which represents a given class field.
	 * 
	 * @param field - represented field
	 * @return a MemberMirror which represents the given field
	 */
	public static MemberMirror create(final Field field) {

		final Class<?> type = field.getType();

		final String name = field.getName();
		final String simpleType = simpleType(field);
		final Set<String> imports = getImports(field);

		if (isListOrSet(type.getName()) && isGeneric(field)) {

			final String collectionType = type.getName();
			final String collectionElementSimpleName = collectionElementSimpleName(field);
			return collectionMirror(
					name,
					simpleType,
					imports,
					collectionType,
					collectionElementSimpleName);
		}
		return simpleMirror(name, simpleType, imports);
	}

	private static String simpleType(final Field field) {

		if (TypeUtils.isGeneric(field)) {
			return removePackageNameFromFullyQualifiedName(field.getGenericType().toString());
		}
		return removePackageNameFromFullyQualifiedName(field.getType().getName());
	}

	private static Set<String> getImports(final Field field) {

		final Set<String> imports = new TreeSet<String>();
		if (!field.getType().isPrimitive()) {
			imports.addAll(createNecessaryImportsForTypeInClass(field.getType().getName()));
		}

		if (isGeneric(field)) {
			imports.addAll(onlyImports(field.getGenericType().toString()));
		}

		return imports;
	}

	private static String collectionElementSimpleName(final Field field) {
		final Type genericType = field.getGenericType();
		final ParameterizedType parameterizedType = (ParameterizedType) genericType;

		Object type = parameterizedType.getActualTypeArguments()[0];
		if(type instanceof ParameterizedType) {
			return removePackageNameFromFullyQualifiedName(type.toString());
		}
		else {
			final Class<?> argumentType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
			return removePackageNameFromFullyQualifiedName(argumentType.getName());
		}
	}

}
