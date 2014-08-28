/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

/**
 * @author Jan van Esdonk
 */
public abstract class ExtendsTestObjectBuilder extends AbstractExtendsTestObjectBuilder<ExtendsTestObjectBuilder> {

	public static ExtendsTestObjectBuilder create() {
		return AbstractBuilderFactory.createImplementation(ExtendsTestObjectBuilder.class);
	}

}
