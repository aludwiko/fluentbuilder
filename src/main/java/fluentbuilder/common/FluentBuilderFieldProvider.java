/* 
 * Created on 09-12-2012 09:35:52 by Andrzej Ludwikowski
 */

package fluentbuilder.common;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


public class FluentBuilderFieldProvider {

	private static final String PACKAGE_REGEXP = "([a-z]*\\.)";
	private static final Predicate<Field> ONLY_PROPER_FIELDS = new Predicate<Field>() {

		@Override
		public boolean apply(Field field) {

			int modifiers = field.getModifiers();

			return !isFinal(modifiers)
					&& !isStatic(modifiers);
		}
	};
	private static final Predicate<Field> ONLY_COLLECTIONS = new Predicate<Field>() {

		@SuppressWarnings("rawtypes")
		private final ImmutableList<Class<? extends Collection>> COLLECTIONS = ImmutableList.of(List.class, Set.class);


		@Override
		public boolean apply(Field field) {

			return COLLECTIONS.contains(field.getType());
		}
	};

	private static final Function<Field, FieldDto> TO_FIELDS_DTO = new Function<Field, FieldDto>() {

		@Override
		public FieldDto apply(Field field) {

			return new FieldDto(field.getName(), fieldType(field));
		}
	};

	private static final Function<Field, FieldDto> TO_FIELDS_DTO_FOR_VARARGS = new Function<Field, FieldDto>() {

		@Override
		public FieldDto apply(Field field) {
			
			String fieldType = fieldTypeForVarArgs(field);
			
			if (fieldType != null){
				
				return new FieldDto(field.getName(), fieldType, field.getType());
			}
			//kolekcja nie jest generyczna więc zostanie pominięta, nulle są następnie odfitrowywane
			return null;

		}
	};


	private static String fieldTypeForVarArgs(Field field) {

		Type type = field.getGenericType();

		if (isGeneric(type)) {

			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type actualType = parameterizedType.getActualTypeArguments()[0];
			// odcinamy class
			return removePackage(actualType.toString()).substring(6);
		}
		//kolekcja nie jest generyczna
		return null;
	}


	private static String fieldType(Field field) {

		Type type = field.getGenericType();

		if (isGeneric(type)) {

			ParameterizedType parameterizedType = (ParameterizedType) type;
			String parameterizedTypeStr = parameterizedType.toString();
			return removePackage(parameterizedTypeStr);
		}

		return field.getType().getSimpleName();
	}

	private static String removePackage(String value) {
		return value.replaceAll(PACKAGE_REGEXP, "");
	}

	private static boolean isGeneric(Type genericType) {
		return genericType instanceof ParameterizedType;
	}


	/**
	 * return only non final non static fields
	 */
	public List<FieldDto> findProperFields(Class<?> clazz) {
		
		List<Field> properFields = newArrayList(filter(newArrayList(clazz.getDeclaredFields()), ONLY_PROPER_FIELDS));

		return newArrayList(transform(properFields, TO_FIELDS_DTO));
	}

	public List<FieldDto> findOnlyCollectionFields(Class<?> clazz) {

		List<Field> properFields = newArrayList(filter(newArrayList(clazz.getDeclaredFields()), ONLY_PROPER_FIELDS));

		List<Field> collections = Lists.newArrayList(Iterables.filter(properFields, ONLY_COLLECTIONS));

		return newArrayList(filter(transform(collections, TO_FIELDS_DTO_FOR_VARARGS), Predicates.notNull()));
	}

}
