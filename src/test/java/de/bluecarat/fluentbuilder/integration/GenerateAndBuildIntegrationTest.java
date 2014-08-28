/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.integration;

import static de.bluecarat.fluentbuilder.integration.samples.DataTypeSampleClassBuilder.aDataTypeSampleClass;
import static de.bluecarat.fluentbuilder.integration.samples.DoubleInheritanceClassBuilder.aDoubleInheritanceClass;
import static de.bluecarat.fluentbuilder.integration.samples.MultiConstructorSampleClassBuilder.aMultiConstructorSampleClass;
import static de.bluecarat.fluentbuilder.integration.samples.SingleInheritanceClassBuilder.aSingleInheritanceClass;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.bluecarat.fluentbuilder.integration.samples.DataTypeSampleClass;
import de.bluecarat.fluentbuilder.integration.samples.DoubleInheritanceClass;
import de.bluecarat.fluentbuilder.integration.samples.MultiConstructorSampleClass;
import de.bluecarat.fluentbuilder.integration.samples.SingleInheritanceClass;

/**
 * @author Jan van Esdonk
 */
public class GenerateAndBuildIntegrationTest {

	@Test
	public void shouldCreateObjectAndSetDoubleInheritatedField() {
		final DoubleInheritanceClass testObject = aDoubleInheritanceClass().withOnlyInTopSampleClass(666).build();
		assertThat(testObject.getOnlyInTopSampleClass(), is(666));
	}

	@Test
	public void shouldCreateObjectAndSetSingleInheritatedField() {
		final SingleInheritanceClass testObject = aSingleInheritanceClass().withOnlyInTopSampleClass(666).build();
		assertThat(testObject.getOnlyInTopSampleClass(), is(666));
	}

	@Test
	public void shouldCreateObjectAndSetHiddenField() {
		final SingleInheritanceClass testObject = aSingleInheritanceClass()
																			.withTopSampleClassDuplicatedInTopAndSingleInheritanceClass(666).build();
		assertThat(testObject.getTopSampleClassDuplicatedInTopAndSingleInheritanceClass(), is(666));
	}

	@Test
	public void shouldCreateObjectWithFirstConstructor() {
		final MultiConstructorSampleClass testObject = aMultiConstructorSampleClass().constructedWith().build();
		assertThat(testObject.getIndexOfConstructorUsedForInitialization(), is(1));
	}

	@Test
	public void shouldCreateObjectWithSecondConstructor() {
		final MultiConstructorSampleClass testObject = aMultiConstructorSampleClass().constructedWithParameter1(2)
																						.build();
		assertThat(testObject.getIndexOfConstructorUsedForInitialization(), is(2));
	}

	@Test
	public void shouldCreateObjectWithThirdConstructor() {
		final MultiConstructorSampleClass testObject = aMultiConstructorSampleClass()
																						.constructedWithParameter1AndParameter2(3, true).build();
		assertThat(testObject.getIndexOfConstructorUsedForInitialization(), is(3));
		assertThat(testObject.getThirdCon(), is(true));
	}

	@Test
	public void shouldSupportObjectType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeDate(new Date()).build();
		assertThat(testObject.getTypeDate(), instanceOf(Date.class));
	}

	@Test
	public void shouldSupportListType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeStringList(new ArrayList<String>())
																		.build();
		assertThat(testObject.getTypeStringList(), instanceOf(ArrayList.class));
	}

	@Test
	public void shouldSupportComplexGenericsType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withComplexGeneric(
				new HashMap<String, List<Integer>>()).build();
		assertThat(testObject.getComplexGeneric(), instanceOf(HashMap.class));
	}

	@Test
	public void shouldSupportBooleanType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeBoolean(true).build();
		assertThat(testObject.getTypeBoolean(), is(true));
	}

	@Test
	public void shouldSupportByteType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeByte(Byte.valueOf("3")).build();
		assertThat(testObject.getTypeByte(), is(Byte.valueOf("3")));
	}

	@Test
	public void shouldSupportCharType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeChar('b').build();
		assertThat(testObject.getTypeChar(), is('b'));
	}

	@Test
	public void shouldSupportDoubleType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeDouble(0.01).build();
		assertThat(testObject.getTypeDouble(), is(0.01));
	}

	@Test
	public void shouldSupportFloatType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeFloat(3).build();
		assertThat(testObject.getTypeFloat(), is(Float.valueOf(3)));
	}

	@Test
	public void shouldSupportIntType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeInt(4).build();
		assertThat(testObject.getTypeInt(), is(4));
	}

	@Test
	public void shouldSupportLongType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeLong(2).build();
		assertThat(testObject.getTypeLong(), is(Long.valueOf(2)));
	}

	@Test
	public void shouldSupportShortType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeShort(Short.valueOf("5")).build();
		assertThat(testObject.getTypeShort(), is(Short.valueOf("5")));
	}

	@Test
	public void shouldSupportStringType() {
		final DataTypeSampleClass testObject = aDataTypeSampleClass().withTypeString("a").build();
		assertThat(testObject.getTypeString(), equalTo("a"));
	}

	public void shouldSetDuplicatedFieldInTopClassFromDoubleInheritedClass() {
		final DoubleInheritanceClass testObject = aDoubleInheritanceClass()
																			.withTopSampleClassDuplicatedInTopAndDoubleInheritanceClass(3).build();
		assertThat(testObject.getTopSampleClassDuplicatedInTopAndDoubleInheritanceClass(), is(3));
	}

	public void shouldSetDuplicatedFieldInTopClassFromInheritedClass() {
		final SingleInheritanceClass testObject = aSingleInheritanceClass()
																			.withTopSampleClassDuplicatedInTopAndSingleInheritanceClass(4).build();
		assertThat(testObject.getTopSampleClassDuplicatedInTopAndSingleInheritanceClass(), is(4));
	}

	public void shouldSetDuplicatedFieldInSingleInheritedClassFromDoubleInheritedClass() {
		final DoubleInheritanceClass testObject = aDoubleInheritanceClass()
																			.withSingleInheritanceClassDuplicatedInSingleAndDoubleInheritanceClass(2).build();
		assertThat(testObject.getSingleInheritanceClassDuplicatedInSingleAndDoubleInheritanceClass(), is(2));
	}

	public void shouldSetFieldInSingleInheritedClassFromDoubleInheritedClass() {
		final DoubleInheritanceClass testObject = aDoubleInheritanceClass().withOnlyInSingleInheritanceClass(3).build();
		assertThat(testObject.getOnlyInSingleInheritanceClass(), is(3));
	}
}
