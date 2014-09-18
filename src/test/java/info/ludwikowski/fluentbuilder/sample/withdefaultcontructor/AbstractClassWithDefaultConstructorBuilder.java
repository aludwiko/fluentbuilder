/*
 * Created on 18 wrz 2014 18:38:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.withdefaultcontructor;


import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

import java.math.BigDecimal;

/**
 * Abstract builder for ClassWithDefaultConstructor.
 * After changes in ClassWithDefaultConstructor this class will be overridden, so don't put any changes here, use
 * ClassWithDefaultConstructorBuilder instead.
 */
public abstract class AbstractClassWithDefaultConstructorBuilder<B> extends AbstractBuilder<ClassWithDefaultConstructor, B> {

	public abstract B withNumber(Integer number);

	public abstract B withClassFormAnotherPackage(BigDecimal classFormAnotherPackage);

}
