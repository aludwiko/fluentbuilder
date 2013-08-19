package info.ludwikowski.generator;

import info.ludwikowski.example.Order;

import org.junit.Test;


public class AbstractBuilderGeneratorUnitTest {

	@Test
	public void shouldPrintBuilder() {

		// given

		// when
		AbstractBuilderGenerator.forClass(Order.class)
								.withBuilderClassPostfix("Budowniczy")
								.withBuildMethodName("buduj")
								.withMethodPrefix("z")
								.withStaticCreate(true)
								.withVarargsForCollections(true)
								.withIgnoredClassPrefix("C")
								.printBuilders();

		// then
	}
}
