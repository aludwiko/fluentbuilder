/*
 * Created on 18 wrz 2014 19:28:27 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.inheritance;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

/**
 * Fluent builder for Child.
 * Don't hesitate to put your custom methods here.
 */
public abstract class ChildBuilder extends AbstractChildBuilder<ChildBuilder> {

	public static ChildBuilder aChild() {
		return AbstractBuilderFactory.createImplementation(ChildBuilder.class);
	}
}
