/* 
 * Created on 03-12-2012 19:37:34 by Andrzej Ludwikowski
 */

package fluentbuilder.simple;

import org.junit.Test;

import fluentbuilder.simple.Sample;
import fluentbuilder.simple.SimpleBuilderGenerator;



public class FluentBuilderGeneratorTest {

	@Test
	public void shouldGenarateBuilderUsingStandardConfiguration() {

		// given

		// when
		SimpleBuilderGenerator.forClass(Sample.class)
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
