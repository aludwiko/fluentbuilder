/* 
 * Created on 22-12-2012 09:35:44 by Andrzej Ludwikowski
 */

package fluentbuilder.generator.abstractproxy;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fluentbuilder.proxy.SampleForTestsBuilder;

import fluentbuilder.generator.abstractproxy.SampleForTests;


public class SampleForTestsTest {

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
		
		// when
		SampleForTests sampleForTests = SampleForTestsBuilder.create().withComplexGeneric(complexGeneric)
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
								.withTypeStringList(typeStringList).build();

		// then
		assertThat(sampleForTests.getTypeByte()).isEqualTo(typeByte);
		assertThat(sampleForTests.getTypeChar()).isEqualTo(typeChar);
		assertThat(sampleForTests.getTypeDate()).isEqualTo(typeDate);
		assertThat(sampleForTests.getTypeDouble()).isEqualTo(typeDouble);
		assertThat(sampleForTests.getTypeFloat()).isEqualTo(typeFloat);
		assertThat(sampleForTests.getTypeInt()).isEqualTo(typeInt);
		assertThat(sampleForTests.getTypeLong()).isEqualTo(typeLong);
		assertThat(sampleForTests.getTypeObjectBoolean()).isEqualTo(typeObjectBoolean);
		assertThat(sampleForTests.isTypeBoolean()).isEqualTo(typeBoolean);
		assertThat(sampleForTests.getTypeObjectByte()).isEqualTo(typeObjectByte);
		assertThat(sampleForTests.getTypeObjectDouble()).isEqualTo(typeObjectDouble);
		assertThat(sampleForTests.getTypeObjectFloat()).isEqualTo(typeObjectFloat);
		assertThat(sampleForTests.getTypeObjectInteger()).isEqualTo(typeObjectInteger);
		assertThat(sampleForTests.getTypeObjectLong()).isEqualTo(typeObjectLong);
		assertThat(sampleForTests.getTypeObjectShort()).isEqualTo(typeObjectShort);
		assertThat(sampleForTests.getTypeString()).isEqualTo(typeString);
		assertThat(sampleForTests.getTypeStringList()).isEqualTo(typeStringList);
		assertThat(sampleForTests.getComplexGeneric()).isEqualTo(complexGeneric);
	}
}
