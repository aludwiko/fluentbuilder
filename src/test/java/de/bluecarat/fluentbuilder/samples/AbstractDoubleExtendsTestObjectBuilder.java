/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import de.bluecarat.fluentbuilder.annotation.ReferencedField;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

/**
 * @author Jan van Esdonk
 */
public abstract class AbstractDoubleExtendsTestObjectBuilder<B> extends AbstractBuilder<DoubleExtendsTestObject, B> {
    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.intField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withIntField(int intField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.charField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withCharField(char charField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.sameField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withSameField(int sameField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.sameField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withBaseTestObjectSameField(int sameField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.sameField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withExtendsTestObjectSameField(int sameField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.uniqueBaseField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withUniqueBaseField(int uniqueBaseField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.uniqueExtendsTestField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withUniqueExtendsField(int uniqueExtendsField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.uniqueDoubleExtendsField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withUniqueDoubleExtendsField(int uniqueDoubleExtendsField);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.duplicateInSuperAndBase")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withDuplicateInSuperAndBase(int duplicateInSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.duplicateInSuperAndBase")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withExtendsTestObjectDuplicateInSuperAndBase(int duplicateInSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.duplicateInSuperSuperAndBase")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withDuplicateInSuperSuperAndBase(int duplicateInSuperSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.duplicateInSuperSuperAndBase")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withBaseTestObjectDuplicateInSuperSuperAndBase(int duplicateInSuperSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.duplicateInSuperAndBase")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withBaseTestObjectDuplicateInSuperAndBase(int duplicateInSuperSuperAndBase);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.duplicateInSuperSuperAndSuper")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withDuplicateInSuperSuperAndSuper(int duplicateInSuperSuperAndSuper);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.duplicateInSuperSuperAndSuper")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withBaseTestObjectDuplicateInSuperSuperAndSuper(int duplicateInSuperSuperAndSuper);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.samePackageField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withSamePackageField(ExtendsTestObject extendsTestObject);

    @ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.randomNameField")
    public abstract AbstractDoubleExtendsTestObjectBuilder<B> withRandomMethodName2131(int number);

    public abstract B constructedWithFirstParameterAndSecondParameter(int firstParameter, int secondParameter);
}
