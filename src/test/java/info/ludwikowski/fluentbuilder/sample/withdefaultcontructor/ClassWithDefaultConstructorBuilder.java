/*
 * Created on 18 wrz 2014 18:38:08 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.withdefaultcontructor;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

import java.math.BigDecimal;

/**
 * Fluent builder for ClassWithDefaultConstructor.
 * Don't hesitate to put your custom methods here.
 */
public abstract class ClassWithDefaultConstructorBuilder extends AbstractClassWithDefaultConstructorBuilder<ClassWithDefaultConstructorBuilder> {

	public static ClassWithDefaultConstructorBuilder aClassWithDefaultConstructor() {
		return AbstractBuilderFactory.createImplementation(ClassWithDefaultConstructorBuilder.class);
	}

	public static ClassWithDefaultConstructorBuilder aClassWithDefaultConstructor(Integer arg0) {
		return AbstractBuilderFactory.createImplementation(ClassWithDefaultConstructorBuilder.class, arg0);
	}

	public static ClassWithDefaultConstructorBuilder aClassWithDefaultConstructor(BigDecimal arg0) {
		return AbstractBuilderFactory.createImplementation(ClassWithDefaultConstructorBuilder.class, arg0);
	}

	public static ClassWithDefaultConstructorBuilder aClassWithDefaultConstructor(Integer arg0, BigDecimal arg1) {
		return AbstractBuilderFactory.createImplementation(ClassWithDefaultConstructorBuilder.class, arg0, arg1);
	}
}