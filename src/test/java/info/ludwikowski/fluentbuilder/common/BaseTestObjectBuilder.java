/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;

/**
 * @author Jan van Esdonk
 */
public abstract class BaseTestObjectBuilder extends AbstractBaseTestObjectBuilder<BaseTestObjectBuilder> {

	public static BaseTestObjectBuilder create() {
		return AbstractBuilderFactory.createImplementation(BaseTestObjectBuilder.class);
	}
}
