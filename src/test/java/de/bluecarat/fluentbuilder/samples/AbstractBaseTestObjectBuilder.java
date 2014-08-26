/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import de.bluecarat.fluentbuilder.annotation.ReferencedField;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

/**
 * @author Jan van Esdonk
 */
public abstract class AbstractBaseTestObjectBuilder<B> extends AbstractBuilder<BaseTestObject, B> {

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withIntField(int intField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withStringField(String stringField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withCharField(char charField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withSameField(int sameField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withUniqueBaseField(int uniqueBaseField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withDuplicateInSuperSuperAndBase(int duplicateInSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withDoubleExtendsTestObjectDuplicateInSuperSuperAndBase(int duplicateInSuperSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withDuplicateInSuperSuperAndSuper(int duplicateInSuperAndSuper);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject")
    public abstract AbstractBaseTestObjectBuilder<B> withExtendsTestObjectDuplicateInSuperSuperAndSuper(int duplicateInSuperSuperAndSuper);

}
