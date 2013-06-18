/* 
 * Created on 18-12-2012 18:36:14 by Andrzej Ludwikowski
 */

package info.ludwikowski.generator;

import info.ludwikowski.example.Order;
import info.ludwikowski.generator.AbstractBuilderGenerator;

import org.junit.Test;



public class AbstractBuilderGeneratorTest {

	@Test
	public void should() {

		// given

		// when
		AbstractBuilderGenerator.forClass(Order.class)
								.withStaticCreate(true)
								.withVarargsForCollections(true)
								.withStaticCreateMethodName("utworz")
								.withBuildMethodName("buduj111")
								.withMethodPrefix("z")
								.withBuilderClassPostfix("Budowniczy")
								.printBuilders();

		// then
	}
}
