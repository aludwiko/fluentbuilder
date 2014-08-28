/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

/**
 * @author Jan van Esdonk
 */
public abstract class ArgTestObjectBuilder extends AbstractArgTestObjectBuilder<ArgTestObjectBuilder> {

	public static ArgTestObjectBuilder create() {
		return AbstractBuilderFactory.createImplementation(ArgTestObjectBuilder.class);
	}

}
