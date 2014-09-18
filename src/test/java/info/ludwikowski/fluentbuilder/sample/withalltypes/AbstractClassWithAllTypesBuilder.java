package info.ludwikowski.fluentbuilder.sample.withalltypes;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Abstract builder for ClassWithAllTypes.
 * After changes in ClassWithAllTypes this class will be overridden, so don't put any changes here, use
 * ClassWithAllTypesBuilder instead.
 */
public abstract class AbstractClassWithAllTypesBuilder<B> extends AbstractBuilder<ClassWithAllTypes, B> {

	public abstract B withTypeByte(byte typeByte);

	public abstract B withTypeObjectByte(Byte typeObjectByte);

	public abstract B withTypeShort(short typeShort);

	public abstract B withTypeObjectShort(Short typeObjectShort);

	public abstract B withTypeInt(int typeInt);

	public abstract B withTypeObjectInteger(Integer typeObjectInteger);

	public abstract B withTypeLong(long typeLong);

	public abstract B withTypeObjectLong(Long typeObjectLong);

	public abstract B withTypeDouble(double typeDouble);

	public abstract B withTypeObjectDouble(Double typeObjectDouble);

	public abstract B withTypeFloat(float typeFloat);

	public abstract B withTypeObjectFloat(Float typeObjectFloat);

	public abstract B withTypeChar(char typeChar);

	public abstract B withTypeDate(Date typeDate);

	public abstract B withTypeBoolean(boolean typeBoolean);

	public abstract B withTypeObjectBoolean(Boolean typeObjectBoolean);

	public abstract B withTypeString(String typeString);

	public abstract B withTypeStringList(List<String> typeStringList);

	@SuppressWarnings("rawtypes")
	public abstract B withTypeStringListWitoutGenerics(List typeStringListWitoutGenerics);

	public abstract B withComplexGeneric(Map<String, List<Integer>> complexGeneric);

	public B withTypeStringList(String... typeStringList) {
		return withTypeStringList(new ArrayList<String>(Arrays.asList(typeStringList)));
	}
}
