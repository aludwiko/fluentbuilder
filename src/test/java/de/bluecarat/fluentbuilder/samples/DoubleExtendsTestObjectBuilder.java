/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

/**
 * @author Jan van Esdonk
 */
public abstract class DoubleExtendsTestObjectBuilder
		extends
		AbstractDoubleExtendsTestObjectBuilder<DoubleExtendsTestObjectBuilder> {

	public static DoubleExtendsTestObjectBuilder create() {
		return AbstractBuilderFactory.createImplementation(DoubleExtendsTestObjectBuilder.class);
	}

}
