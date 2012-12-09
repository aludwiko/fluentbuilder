/* 
 * Created on 09-12-2012 09:35:52 by Andrzej Ludwikowski
 */

package fluentbuilder;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Field;
import java.util.List;

import com.google.common.base.Predicate;


public class FluentBuilderFieldProvider {

	private static final Predicate<Field> ONLY_PROPER_FIELDS = new Predicate<Field>() {

		public boolean apply(Field field) {

			int modifiers = field.getModifiers();

			return !isFinal(modifiers)
					&& !isStatic(modifiers);
		}
	};


	/**
	 * return only non final non static fields
	 */
	public List<Field> findProperFields(Class<?> clazz) {
		
		return newArrayList(filter(newArrayList(clazz.getDeclaredFields()), ONLY_PROPER_FIELDS));
	}

}
