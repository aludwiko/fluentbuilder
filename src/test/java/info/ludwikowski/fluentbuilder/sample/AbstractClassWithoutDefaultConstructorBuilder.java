/* 
 * Created on 11 wrz 2014 19:56:05 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;


/**
 * Abstract builder for ClassWithoutDefaultConstructor.
 * After changes in ClassWithoutDefaultConstructor this class will be overridden, so don't put any changes here, use
 * ClassWithoutDefaultConstructorBuilder instead.
 */
public abstract class AbstractClassWithoutDefaultConstructorBuilder<B> extends AbstractBuilder<ClassWithoutDefaultConstructor, B> {

	public abstract B withNumber(Integer number);

	public abstract B withSomeString(String someString);

}
