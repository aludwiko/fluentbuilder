/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import de.bluecarat.fluentbuilder.annotation.ReferencedField;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

/**
 * @author Jan van Esdonk
 */
public abstract class AbstractExtendsTestObjectBuilder<B> extends AbstractBuilder<ExtendsTestObject, B> {

	@ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.intField")
	public abstract AbstractExtendsTestObjectBuilder<B> withIntField(int intField);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.charField")
	public abstract AbstractExtendsTestObjectBuilder<B> withCharField(char charField);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.sameField")
	public abstract AbstractExtendsTestObjectBuilder<B> withSameField(int sameField);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.sameField")
	public abstract AbstractExtendsTestObjectBuilder<B> withBaseTestObjectSameField(int sameField);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.uniqueBaseField")
	public abstract AbstractExtendsTestObjectBuilder<B> withUniqueBaseField(int uniqueBaseField);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.uniqueExtendsField")
	public abstract AbstractExtendsTestObjectBuilder<B> withUniqueExtendsField(int uniqueExtendsField);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.duplicateInSuperAndSuper")
	public abstract AbstractExtendsTestObjectBuilder<B> withDuplicateInSuperSuperAndSuper(int duplicateInSuperAndSuper);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.duplicateInSuperSuperAndSuper")
	public abstract AbstractExtendsTestObjectBuilder<B> withBaseTestObjectDuplicateInSuperSuperAndSuper(int duplicateInSuperSuperAndSuper);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObjectTestObject.duplicateInSuperAndBase")
	public abstract AbstractExtendsTestObjectBuilder<B> withDuplicateInSuperAndBase(int duplicateInSuperAndBase);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.duplicateInSuperAndBase")
	public abstract AbstractExtendsTestObjectBuilder<B> withExtendsTestObjectDuplicateInSuperAndBase(int duplicateInSuperAndBase);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.ExtendsTestObject.BaseTestObjectNameConflict")
	public abstract AbstractExtendsTestObjectBuilder<B> withBaseTestObjectNameConflict(int duplicate);

	@ReferencedField("de.bluecarat.fluentbuilder.samples.BaseTestObject.BaseTestObjectNameConflict")
	public abstract AbstractExtendsTestObjectBuilder<B> withBaseTestObjectBaseTestObjectNameConflict(int duplicate);
}
