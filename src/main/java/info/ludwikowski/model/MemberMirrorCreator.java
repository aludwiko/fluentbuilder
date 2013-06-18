package info.ludwikowski.model;

import static info.ludwikowski.util.StringUtils.removePackage;
import info.ludwikowski.common.Context;
import info.ludwikowski.util.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class MemberMirrorCreator {

	public static MemberMirror create(Field field, Context context) {

		Class<?> type = field.getType();

		String name = field.getName();
		String simpleType = simpleType(field);
		Set<String> imports = imports(field);

		if (TypeUtils.isListOrSet(type.getName()) && isGeneric(field)) {

			String collectionType = type.getName();
			String collectionElementSimpleName = collectionElementSimpleName(field);
			return MemberMirrorImpl.collectionMirror(name, simpleType, imports, collectionType, collectionElementSimpleName);
		}
		return MemberMirrorImpl.simpleMirror(name, simpleType, imports);
	}

	private static String simpleType(Field field) {

		if (isGeneric(field)) {
			return removePackage(field.getGenericType().toString());
		}
		return removePackage(field.getType().getName());
	}

	private static Set<String> imports(Field field) {

		Set<String> imports = new TreeSet<String>();

		if (!field.getType().isPrimitive()) {
			imports.addAll(ImportsHelper.createNecessaryImports(field.getType().getName()));
		}
		
		if (isGeneric(field)) {
			imports.addAll(ImportsHelper.onlyImports(field.getGenericType().toString()));
		}

		return imports;
	}

	private static String collectionElementSimpleName(Field field) {

		Type genericType = field.getGenericType();

		ParameterizedType parameterizedType = (ParameterizedType) genericType;

		Class<?> type = (Class<?>) parameterizedType.getActualTypeArguments()[0];

		return removePackage(type.getName());
	}

	private static boolean isGeneric(Field field) {

		Type genericType = field.getGenericType();

		if (genericType instanceof ParameterizedType) {

			ParameterizedType parameterizedType = (ParameterizedType) genericType;

			return parameterizedType.getActualTypeArguments().length > 0;
		}

		return false;
	}

}
