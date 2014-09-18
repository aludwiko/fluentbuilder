/*
 * Created on 22-12-2012 09:35:44 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.withalltypes;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;




public class ClassWithAllTypesTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void shouldCreateObjectByBuilder() {

		// given
		Map<String, List<Integer>> complexGeneric = Maps.newHashMap();
		boolean typeBoolean = true;
		byte typeByte = 12;
		char typeChar = 'a';
		Date typeDate = new Date();
		double typeDouble = 33.3d;
		float typeFloat = 44.4f;
		int typeInt = 111;
		long typeLong = 222;
		Boolean typeObjectBoolean = true;
		Byte typeObjectByte = 14;
		Double typeObjectDouble = 55d;
		Float typeObjectFloat = 66f;
		Integer typeObjectInteger = 44;
		Long typeObjectLong = 55l;
		short typeShort =66;
		Short typeObjectShort = 77;
		String typeString = "string";
		List<String> typeStringList = Lists.newArrayList();
		List typeStringListWitoutGenerics = new LinkedList();

		// when
		ClassWithAllTypes classWithAllTypes = ClassWithAllTypesBuilder.aClassWithAllTypes().withComplexGeneric(complexGeneric)
								.withTypeBoolean(typeBoolean)
								.withTypeByte(typeByte)
								.withTypeChar(typeChar)
								.withTypeDate(typeDate)
								.withTypeDouble(typeDouble)
								.withTypeFloat(typeFloat)
								.withTypeInt(typeInt)
								.withTypeLong(typeLong)
								.withTypeShort(typeShort)
								.withTypeObjectBoolean(typeObjectBoolean)
								.withTypeObjectByte(typeObjectByte)
								.withTypeObjectDouble(typeObjectDouble)
								.withTypeObjectFloat(typeObjectFloat)
								.withTypeObjectInteger(typeObjectInteger)
								.withTypeObjectLong(typeObjectLong)
								.withTypeObjectShort(typeObjectShort)
								.withTypeString(typeString)
																	.withTypeStringList(typeStringList)
																	.withTypeStringListWitoutGenerics(typeStringListWitoutGenerics)
																	.build();

		// then
		assertThat(classWithAllTypes.getTypeByte()).isEqualTo(typeByte);
		assertThat(classWithAllTypes.getTypeChar()).isEqualTo(typeChar);
		assertThat(classWithAllTypes.getTypeDate()).isEqualTo(typeDate);
		assertThat(classWithAllTypes.getTypeDouble()).isEqualTo(typeDouble);
		assertThat(classWithAllTypes.getTypeFloat()).isEqualTo(typeFloat);
		assertThat(classWithAllTypes.getTypeInt()).isEqualTo(typeInt);
		assertThat(classWithAllTypes.getTypeLong()).isEqualTo(typeLong);
		assertThat(classWithAllTypes.getTypeObjectBoolean()).isEqualTo(typeObjectBoolean);
		assertThat(classWithAllTypes.isTypeBoolean()).isEqualTo(typeBoolean);
		assertThat(classWithAllTypes.getTypeObjectByte()).isEqualTo(typeObjectByte);
		assertThat(classWithAllTypes.getTypeObjectDouble()).isEqualTo(typeObjectDouble);
		assertThat(classWithAllTypes.getTypeObjectFloat()).isEqualTo(typeObjectFloat);
		assertThat(classWithAllTypes.getTypeObjectInteger()).isEqualTo(typeObjectInteger);
		assertThat(classWithAllTypes.getTypeObjectLong()).isEqualTo(typeObjectLong);
		assertThat(classWithAllTypes.getTypeObjectShort()).isEqualTo(typeObjectShort);
		assertThat(classWithAllTypes.getTypeString()).isEqualTo(typeString);
		assertThat(classWithAllTypes.getTypeStringList()).isEqualTo(typeStringList);
		assertThat(classWithAllTypes.getComplexGeneric()).isEqualTo(complexGeneric);
		assertThat(classWithAllTypes.getTypeStringListWitoutGenerics()).isEqualTo(typeStringListWitoutGenerics);

	}
}
