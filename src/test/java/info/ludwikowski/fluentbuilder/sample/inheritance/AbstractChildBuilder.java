/*
 * Created on 18 wrz 2014 19:28:35 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.inheritance;


import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

/**
 * Abstract builder for Child.
 * After changes in Child this class will be overridden, so don't put any changes here, use ChildBuilder instead.
 */
public abstract class AbstractChildBuilder<B> extends AbstractBuilder<Child, B> {

	public abstract B withChildField(String childField);

	public abstract B withParentField(String parentField);

	public abstract B withGranField(String granField);

}
