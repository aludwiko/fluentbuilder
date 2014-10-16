/*
 * Created on 18 wrz 2014 19:29:58 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sampletest.inheritance;

import static info.ludwikowski.fluentbuilder.sample.inheritance.ChildBuilder.aChild;
import static org.fest.assertions.api.Assertions.assertThat;
import info.ludwikowski.fluentbuilder.sample.inheritance.Child;

import org.junit.Test;


public class ChildTest {

	@Test
	public void shouldCreateChildWithInheritedFields() {

		// given
		String childField = "childField";
		String parentField = "parentField";
		String granField = "granField";

		// when
		Child child = aChild().withChildField(childField)
								.withParentField(parentField)
								.withGranField(granField)
								.build();

		// then
		assertThat(child.getChildField()).isEqualTo(childField);
		assertThat(child.getParentField()).isEqualTo(parentField);
		assertThat(child.getGranField()).isEqualTo(granField);

	}
}
