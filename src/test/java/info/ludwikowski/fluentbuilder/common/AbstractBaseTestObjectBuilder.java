/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;


/**
 * @author Jan van Esdonk
 */
public abstract class AbstractBaseTestObjectBuilder<B> extends AbstractBuilder<BaseTestObject, B> {

	public abstract AbstractBaseTestObjectBuilder<B> withIntField(int intField);

	public abstract AbstractBaseTestObjectBuilder<B> withStringField(String stringField);

	public abstract AbstractBaseTestObjectBuilder<B> withCharField(char charField);

	public abstract AbstractBaseTestObjectBuilder<B> withSameField(int sameField);

	public abstract AbstractBaseTestObjectBuilder<B> withUniqueBaseField(int uniqueBaseField);
}
