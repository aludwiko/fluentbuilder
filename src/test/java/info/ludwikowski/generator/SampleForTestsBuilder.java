/* 
 * Created on 22-12-2012 09:00:56 by Andrzej Ludwikowski
 */

package info.ludwikowski.generator;

import info.ludwikowski.common.AbstractBuilder;
import info.ludwikowski.common.AbstractBuilderFactory;
import info.ludwikowski.example.SampleForTests;

import java.util.Date;
import java.util.List;
import java.util.Map;



/** 
 * Fluent builder for SampleForTests
 * 
 */
public abstract class SampleForTestsBuilder extends AbstractBuilder<SampleForTests, SampleForTestsBuilder> {

	public static final String getPrefix() {
		return "with";
	}

	public SampleForTests buduj() {
		return build();
	}

	public abstract SampleForTestsBuilder withTypeByte(byte typeByte);

	public abstract SampleForTestsBuilder withTypeObjectByte(Byte typeObjectByte);

	public abstract SampleForTestsBuilder withTypeShort(short typeShort);

	public abstract SampleForTestsBuilder withTypeObjectShort(Short typeObjectShort);

	public abstract SampleForTestsBuilder withTypeInt(int typeInt);

	public abstract SampleForTestsBuilder withTypeObjectInteger(Integer typeObjectInteger);

	public abstract SampleForTestsBuilder withTypeLong(long typeLong);

	public abstract SampleForTestsBuilder withTypeObjectLong(Long typeObjectLong);

	public abstract SampleForTestsBuilder withTypeDouble(double typeDouble);

	public abstract SampleForTestsBuilder withTypeObjectDouble(Double typeObjectDouble);

	public abstract SampleForTestsBuilder withTypeFloat(float typeFloat);

	public abstract SampleForTestsBuilder withTypeObjectFloat(Float typeObjectFloat);

	public abstract SampleForTestsBuilder withTypeChar(char typeChar);

	public abstract SampleForTestsBuilder withTypeDate(Date typeDate);

	public abstract SampleForTestsBuilder withTypeBoolean(boolean typeBoolean);

	public abstract SampleForTestsBuilder withTypeObjectBoolean(Boolean typeObjectBoolean);

	public abstract SampleForTestsBuilder withTypeString(String typeString);

	public abstract SampleForTestsBuilder withTypeStringList(List<String> typeStringList);

	public abstract SampleForTestsBuilder withComplexGeneric(Map<String, List<Integer>> complexGeneric);

	public static SampleForTestsBuilder create() {
		return AbstractBuilderFactory.createImplementation(SampleForTestsBuilder.class);
	}
}
