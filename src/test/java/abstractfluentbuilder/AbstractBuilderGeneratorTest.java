/* 
 * Created on 18-12-2012 18:36:14 by Andrzej Ludwikowski
 */

package abstractfluentbuilder;

import org.junit.Test;


public class AbstractBuilderGeneratorTest {

	@Test
	public void should() {

		// given

		// when
		AbstractBuilderGenerator.forClass(SampleForTests.class).printBuilder();

		// then
	}
}
