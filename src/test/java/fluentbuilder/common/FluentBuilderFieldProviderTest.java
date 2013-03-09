/* 
 * Created on 09-12-2012 09:53:29 by Andrzej Ludwikowski
 */

package fluentbuilder.common;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import fluentbuilder.generator.simple.Sample;


public class FluentBuilderFieldProviderTest {

	private FluentBuilderFieldProvider fieldProvider = new FluentBuilderFieldProvider();


	@Test
	public void shouldReturnOnlyProperFields() {

		// given

		// when
		List<FieldDto> fields = fieldProvider.findProperFields(Sample.class);

		// then
		assertThat(fields).containsOnly(new FieldDto("id", "int"),
				new FieldDto("name", "String"),
				new FieldDto("map", "Map<String, List<Integer>>"));
	}

}
