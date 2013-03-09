/* 
 * Created on 18-12-2012 18:36:14 by Andrzej Ludwikowski
 */

package fluentbuilder.generator.abstractproxy;

import org.junit.Test;

import fluentbuilder.example.Order;
import fluentbuilder.generator.abstractproxy.AbstractBuilderGenerator;


public class AbstractBuilderGeneratorTest {

	@Test
	public void should() {

		// given

		// when
		AbstractBuilderGenerator.forClass(Order.class)
								.withStaticCreate(true)
								.withVarargsForCollections(true)
								.printBuilder();

		// then
	}
}
