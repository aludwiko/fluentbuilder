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
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;


public class FluentBuilderFieldProvider {

	private static final Predicate<Field> ONLY_PROPER_FIELDS = new Predicate<Field>() {

		@Override
		public boolean apply(Field field) {

			int modifiers = field.getModifiers();

			return !isFinal(modifiers)
					&& !isStatic(modifiers);
		}
	};
	private static final Function<Field, FieldDto> TO_FIELDS_DTO = new Function<Field, FieldDto>() {

		@Override
		public FieldDto apply(Field field) {

			return new FieldDto(field.getName(), fieldType(field));
		}

		private String fieldType(Field field) {

			Type type = field.getGenericType();

			if (isGeneric(type)) {

				ParameterizedType parameterizedType = (ParameterizedType) type;
				String parameterizedTypeStr = parameterizedType.toString();
				return parameterizedTypeStr.replaceAll("([a-z]*\\.)", "");
			}

			return field.getType().getSimpleName();
		}

		private boolean isGeneric(Type genericType) {
			return genericType instanceof ParameterizedType;
		}
	};


	/**
	 * return only non final non static fields
	 */
	public List<FieldDto> findProperFields(Class<?> clazz) {
		
		List<Field> properFields = newArrayList(filter(newArrayList(clazz.getDeclaredFields()), ONLY_PROPER_FIELDS));

		return newArrayList(transform(properFields, TO_FIELDS_DTO));
	}

}
