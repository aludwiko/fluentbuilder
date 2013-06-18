package info.ludwikowski.example;

import info.ludwikowski.common.AbstractBuilderFactory;


/**
 * Fluent builder for SampleForTests.
 * Don't hesitate to put your custom methods here.
 */
public abstract class SampleForTestsBuilder extends AbstractSampleForTestsBuilder<SampleForTestsBuilder> {

	public static SampleForTestsBuilder create() {
		return AbstractBuilderFactory.createImplementation(SampleForTestsBuilder.class);
	}
}