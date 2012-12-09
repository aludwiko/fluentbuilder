/* 
 * Created on 03-12-2012 19:37:34 by Andrzej Ludwikowski
 */

package fluentbuilder;

import org.junit.Test;


public class FluentBuilderGeneratorTest {

	@Test
	public void shouldGenarateBuilderUsingStandardConfiguration() {

		// given

		// when
		FluentBuilderGenerator.forClass(Sample.class)
								.printBuilder();

		// then
	}

	@Test
	public void shouldGenarateBuilderUsingStandardCustomeConfiguration() {

		// given

		// when
		FluentBuilderGenerator.forClass(Sample.class)
								.withBuilderName("Budowiczy")
								.withLaunchBuildMethodName("buduj")
								.withMethodPrefix("z")
								.printBuilder();

		// then
	}
}
