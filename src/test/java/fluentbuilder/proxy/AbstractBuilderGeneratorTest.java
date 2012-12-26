/* 
 * Created on 18-12-2012 18:36:14 by Andrzej Ludwikowski
 */

package fluentbuilder.proxy;

import org.junit.Test;


public class AbstractBuilderGeneratorTest {

	@Test
	public void should() {

		// given

		// when
		AbstractBuilderGenerator.forClass(SampleForTests.class)
								.withStaticCreate(true)
								.printBuilder();

		// then
	}
}
