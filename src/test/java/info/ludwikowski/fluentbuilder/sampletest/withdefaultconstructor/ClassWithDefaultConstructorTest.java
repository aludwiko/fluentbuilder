/*
 * Created on 18 wrz 2014 18:38:59 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sampletest.withdefaultconstructor;

import static info.ludwikowski.fluentbuilder.sample.withdefaultcontructor.ClassWithDefaultConstructorBuilder.aClassWithDefaultConstructor;
import static org.fest.assertions.api.Assertions.assertThat;
import info.ludwikowski.fluentbuilder.sample.withdefaultcontructor.ClassWithDefaultConstructor;

import java.math.BigDecimal;

import org.junit.Test;


public class ClassWithDefaultConstructorTest {

	@Test
	public void shouldCreateObjectUsingPrivateConstructor() {

		//given
		Integer number = 1;

		//when
		ClassWithDefaultConstructor result = aClassWithDefaultConstructor().withNumber(number).build();

		//then
		assertThat(result.getNumber()).isEqualTo(number);
	}

	@Test
	public void shouldCreateObjectUsingPackagePrivateConstructor() {

		// given
		Integer number = 1;

		// when
		ClassWithDefaultConstructor result = aClassWithDefaultConstructor(number).build();

		// then
		assertThat(result.getNumber()).isEqualTo(number);
	}

	@Test
	public void shouldCreateObjectUsingProtectedConstructor() {

		// given
		BigDecimal number = BigDecimal.ZERO;

		// when
		ClassWithDefaultConstructor result = aClassWithDefaultConstructor(number).build();

		// then
		assertThat(result.getClassFormAnotherPackage()).isEqualTo(number);
	}

	@Test
	public void shouldCreateObjectUsingPublicConstructor() {

		// given
		Integer number = 1;
		BigDecimal someBigDecimal = BigDecimal.ZERO;

		// when
		ClassWithDefaultConstructor result = aClassWithDefaultConstructor(number, someBigDecimal).build();

		// then
		assertThat(result.getClassFormAnotherPackage()).isEqualTo(someBigDecimal);
		assertThat(result.getNumber()).isEqualTo(number);
	}
}
