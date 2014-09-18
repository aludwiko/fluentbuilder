/* 
 * Created on 31 sie 2014 21:48:35 by Andrzej
 */

package info.ludwikowski.fluentbuilder.sample.withalltypes;

import info.ludwikowski.fluentbuilder.common.AbstractBuilderFactory;


/**
 * Fluent builder for ClassWithAllTypes.
 * Don't hesitate to put your custom methods here.
 */
public abstract class ClassWithAllTypesBuilder extends AbstractClassWithAllTypesBuilder<ClassWithAllTypesBuilder> {

	public static ClassWithAllTypesBuilder aClassWithAllTypes() {
		return AbstractBuilderFactory.createImplementation(ClassWithAllTypesBuilder.class);
	}
}
