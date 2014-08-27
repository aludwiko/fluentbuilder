/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import java.util.Date;

import de.bluecarat.fluentbuilder.annotation.ReferencedField;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

/**
 * @author Jan van Esdonk
 */
public abstract class AbstractArgTestObjectBuilder<B> extends AbstractBuilder<ArgTestObject, B> {

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withIntField(int intField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withShortField(short shortField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withFloatField(float floatField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withByteField(byte byteField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withLongField(long longField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withBooleanField(boolean booleanField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withObjectField(Date objectField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withDoubleField(double doubleField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withCharField(char charField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withInheritatedField(int inheritatedField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ArgTestObject")
    public abstract AbstractArgTestObjectBuilder<B> withSameField(int sameField);

}
