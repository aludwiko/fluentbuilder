/*
 * Created on 11 wrz 2014 19:56:31 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

/**
 * Fluent builder for ClassWithoutDefaultConstructor.
 * Don't hesitate to put your custom methods here.
 */
public abstract class ClassWithoutDefaultConstructorBuilder extends AbstractClassWithoutDefaultConstructorBuilder<ClassWithoutDefaultConstructorBuilder> {

	public static ClassWithoutDefaultConstructorBuilder aClassWithoutDefaultConstructor(Integer arg0) {
		return AbstractBuilderFactory.createImplementation(ClassWithoutDefaultConstructorBuilder.class, arg0);
	}

	public static ClassWithoutDefaultConstructorBuilder aClassWithoutDefaultConstructor(String arg0) {
		return AbstractBuilderFactory.createImplementation(ClassWithoutDefaultConstructorBuilder.class, arg0);
	}

	public static ClassWithoutDefaultConstructorBuilder aClassWithoutDefaultConstructor(Integer arg0, String arg1) {
		return AbstractBuilderFactory.createImplementation(ClassWithoutDefaultConstructorBuilder.class, arg0, arg1);
	}
}