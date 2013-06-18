package info.ludwikowski.generator;


import info.ludwikowski.common.AbstractBuilder;
import info.ludwikowski.common.AbstractBuilderFactory;
import info.ludwikowski.example.SampleForTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Abstract builder for SampleForTests.
 */
public abstract class SampleForTestsBudowniczy extends AbstractBuilder<SampleForTestsBudowniczy, SampleForTests> {

	public static SampleForTestsBudowniczy utworz() {
		return AbstractBuilderFactory.createImplementation(SampleForTestsBudowniczy.class);
	}

	public static final String getPrefix() {
		return "z";
	}

	public SampleForTestsBudowniczy buduj() {
		return build();
	}

	public abstract SampleForTestsBudowniczy zTypeByte(byte typeByte);

	public abstract SampleForTestsBudowniczy zTypeObjectByte(Byte typeObjectByte);

	public abstract SampleForTestsBudowniczy zTypeShort(short typeShort);

	public abstract SampleForTestsBudowniczy zTypeObjectShort(Short typeObjectShort);

	public abstract SampleForTestsBudowniczy zTypeInt(int typeInt);

	public abstract SampleForTestsBudowniczy zTypeObjectInteger(Integer typeObjectInteger);

	public abstract SampleForTestsBudowniczy zTypeLong(long typeLong);

	public abstract SampleForTestsBudowniczy zTypeObjectLong(Long typeObjectLong);

	public abstract SampleForTestsBudowniczy zTypeDouble(double typeDouble);

	public abstract SampleForTestsBudowniczy zTypeObjectDouble(Double typeObjectDouble);

	public abstract SampleForTestsBudowniczy zTypeFloat(float typeFloat);

	public abstract SampleForTestsBudowniczy zTypeObjectFloat(Float typeObjectFloat);

	public abstract SampleForTestsBudowniczy zTypeChar(char typeChar);

	public abstract SampleForTestsBudowniczy zTypeDate(Date typeDate);

	public abstract SampleForTestsBudowniczy zTypeBoolean(boolean typeBoolean);

	public abstract SampleForTestsBudowniczy zTypeObjectBoolean(Boolean typeObjectBoolean);

	public abstract SampleForTestsBudowniczy zTypeString(String typeString);

	public abstract SampleForTestsBudowniczy zTypeStringList(List<String> typeStringList);

	public abstract SampleForTestsBudowniczy zComplexGeneric(Map<String, List<Integer>> complexGeneric);

	public SampleForTestsBudowniczy zTypeStringList(String... typeStringList) {
		return zTypeStringList(new ArrayList<String>(Arrays.asList(typeStringList)));
	}
}
