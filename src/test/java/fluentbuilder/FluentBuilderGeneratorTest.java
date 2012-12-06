/* 
 * Created on 03-12-2012 19:37:34 by Andrzej Ludwikowski
 */

package fluentbuilder;

import org.junit.Test;


public class FluentBuilderGeneratorTest {

	@Test
	public void should() {

		// given

		// when
		FluentBuilderGenerator.forClass(FluentBuilderGenerator.class)
								.withBuilderName("Budowniczy")
								.printBuilder();

		// then
	}
}
