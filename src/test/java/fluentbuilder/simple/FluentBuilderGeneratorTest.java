/* 
 * Created on 03-12-2012 19:37:34 by Andrzej Ludwikowski
 */

package fluentbuilder.simple;

import org.junit.Test;



public class FluentBuilderGeneratorTest {

	@Test
	public void shouldGenarateBuilderUsingStandardConfiguration() {

		// given

		// when
		SimpleBuilderGenerator.forClass(Sample.class)
								.withMethodPrefix("with")
								.printBuilder();

		// then
	}

	@Test
	public void shouldGenarateBuilderUsingStandardCustomeConfiguration() {

		// given

		// when
		SimpleBuilderGenerator.forClass(Sample.class)
								.withBuilderName("Budowiczy")
								.withLaunchBuildMethodName("buduj")
								.withMethodPrefix("z")
								.printBuilder();

		// then
	}
}
