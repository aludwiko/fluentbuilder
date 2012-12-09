/* 
 * Created on 09-12-2012 09:53:29 by Andrzej Ludwikowski
 */

package fluentbuilder;

import static org.fest.assertions.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


public class FluentBuilderFieldProviderTest {

	private FluentBuilderFieldProvider fieldProvider = new FluentBuilderFieldProvider();


	@Test
	public void shouldReturnOnlyProperFields() {

		// given

		// when
		List<Field> fields = fieldProvider.findProperFields(Sample.class);

		// then
		assertThat(fieldNames(fields)).containsOnly("id", "name");
	}

	private List<String> fieldNames(List<Field> fields) {

		return Lists.newArrayList(Iterables.transform(fields, new Function<Field, String>() {

			public String apply(Field field) {

				return field.getName();
			}
		}));
	}
}
