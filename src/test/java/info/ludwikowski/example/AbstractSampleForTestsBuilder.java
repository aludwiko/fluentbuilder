package info.ludwikowski.example;

import info.ludwikowski.common.AbstractBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Abstract builder for SampleForTests.
 * After changes in SampleForTests this class will be overriden, so don't put any changes here, use
 * SampleForTestsBuilder instead.
 */
public abstract class AbstractSampleForTestsBuilder<B> extends AbstractBuilder<SampleForTests, B> {

	public abstract AbstractSampleForTestsBuilder<B> withTypeByte(byte typeByte);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectByte(Byte typeObjectByte);

	public abstract AbstractSampleForTestsBuilder<B> withTypeShort(short typeShort);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectShort(Short typeObjectShort);

	public abstract AbstractSampleForTestsBuilder<B> withTypeInt(int typeInt);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectInteger(Integer typeObjectInteger);

	public abstract AbstractSampleForTestsBuilder<B> withTypeLong(long typeLong);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectLong(Long typeObjectLong);

	public abstract AbstractSampleForTestsBuilder<B> withTypeDouble(double typeDouble);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectDouble(Double typeObjectDouble);

	public abstract AbstractSampleForTestsBuilder<B> withTypeFloat(float typeFloat);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectFloat(Float typeObjectFloat);

	public abstract AbstractSampleForTestsBuilder<B> withTypeChar(char typeChar);

	public abstract AbstractSampleForTestsBuilder<B> withTypeDate(Date typeDate);

	public abstract AbstractSampleForTestsBuilder<B> withTypeBoolean(boolean typeBoolean);

	public abstract AbstractSampleForTestsBuilder<B> withTypeObjectBoolean(Boolean typeObjectBoolean);

	public abstract AbstractSampleForTestsBuilder<B> withTypeString(String typeString);

	public abstract AbstractSampleForTestsBuilder<B> withTypeStringList(List<String> typeStringList);

	public abstract AbstractSampleForTestsBuilder<B> withComplexGeneric(Map<String, List<Integer>> complexGeneric);

	public AbstractSampleForTestsBuilder<B> withTypeStringList(String... typeStringList) {
		return withTypeStringList(new ArrayList<String>(Arrays.asList(typeStringList)));
	}
}
