/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.model.ImportsFactory.createNecessaryImportsForTypeInClass;
import static info.ludwikowski.fluentbuilder.util.TypeUtils.isGeneric;
import info.ludwikowski.fluentbuilder.common.Context;
import info.ludwikowski.fluentbuilder.util.NameUtils;
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
	 * @param context - settings for the builder
	 * @return a MemberMirror which represents the given field
	 */
	public static MemberMirror create(final Field field, final Context context) {

		final Class<?> type = field.getType();

		final String name = field.getName();
		final String simpleType = simpleType(field);
		final Set<String> imports = getImports(field);

		if (TypeUtils.isListOrSet(type.getName()) && TypeUtils.isGeneric(field)) {

			final String collectionType = type.getName();
			final String collectionElementSimpleName = collectionElementSimpleName(field);
			return MemberMirrorImpl.collectionMirror(
					name,
					simpleType,
					imports,
					collectionType,
					collectionElementSimpleName);
		}
		return MemberMirrorImpl.simpleMirror(name, simpleType, imports);
	}

	private static String simpleType(final Field field) {

		if (TypeUtils.isGeneric(field)) {
			return NameUtils.removePackageNameFromFullyQualifiedName(field.getGenericType().toString());
		}
		return NameUtils.removePackageNameFromFullyQualifiedName(field.getType().getName());
	}

	private static Set<String> getImports(final Field field) {

		final Set<String> imports = new TreeSet<String>();
		if (!field.getType().isPrimitive()) {

			imports.addAll(createNecessaryImportsForTypeInClass(field.getType().getName()));
		}

		if (isGeneric(field)) {
			imports.addAll(ImportsFactory.onlyImports(field.getGenericType().toString()));
		}

		return imports;
	}

	private static String collectionElementSimpleName(final Field field) {
		final Type genericType = field.getGenericType();
		final ParameterizedType parameterizedType = (ParameterizedType) genericType;
		final Class<?> type = (Class<?>) parameterizedType.getActualTypeArguments()[0];
		return NameUtils.removePackageNameFromFullyQualifiedName(type.getName());
	}

}
