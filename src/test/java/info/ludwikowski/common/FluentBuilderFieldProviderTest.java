/* 
 * Created on 09-12-2012 09:53:29 by Andrzej Ludwikowski
 */

package info.ludwikowski.common;

import static org.fest.assertions.api.Assertions.assertThat;
import info.ludwikowski.generator.simple.Sample;

import java.util.List;

import org.junit.Test;



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
