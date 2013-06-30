package info.ludwikowski.generator;

import info.ludwikowski.example.COrder;

import org.junit.Test;


public class AbstractBuilderGeneratorUnitTest {

	@Test
	public void shouldPrintBuilder() {

		// given

		// when
		AbstractBuilderGenerator.forClass(COrder.class)
								.withBuilderClassPostfix("Budowniczy")
								.withBuildMethodName("buduj")
								.withMethodPrefix("z")
								.withStaticCreate(true)
								.withVarargsForCollections(true)
								.withIgnoredClassPrefix("C")
								.printSingleBuilder();

		// then
	}
}
