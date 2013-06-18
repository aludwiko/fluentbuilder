package info.ludwikowski.generator;

import info.ludwikowski.example.SampleForTests;

import org.junit.Test;


public class AbstractBuilderGeneratorUnitTest {

	@Test
	public void shouldPrintBuilder() {

		// given

		// when
		AbstractBuilderGenerator.forClass(SampleForTests.class)
								.withBuilderClassPostfix("Budowniczy")
								.withBuildMethodName("buduj")
								.withMethodPrefix("z")
								.withStaticCreate(true)
								.withStaticCreateMethodName("utworz")
								.withVarargsForCollections(true)
								.printSingleBuilder();

		// then
	}
}
