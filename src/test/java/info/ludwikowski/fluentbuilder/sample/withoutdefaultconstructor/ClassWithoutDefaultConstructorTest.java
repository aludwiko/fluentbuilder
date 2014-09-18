/*
 * Created on 11 wrz 2014 20:32:59 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.withoutdefaultconstructor;

import static info.ludwikowski.fluentbuilder.sample.withoutdefaultconstructor.ClassWithoutDefaultConstructorBuilder.aClassWithoutDefaultConstructor;
import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;



public class ClassWithoutDefaultConstructorTest {

	@Test
	public void shouldCreateObjectWithNotDefaultConstructor() {

		// given
		Integer number = 1;

		// when
		ClassWithoutDefaultConstructor classWithoutDefaultConstructor = aClassWithoutDefaultConstructor(number).build();

		// then
		assertThat(classWithoutDefaultConstructor.getNumber()).isEqualTo(number);
	}

	@Test
	public void shouldCreateObjectWithAnotherNotDefaultConstructor() {

		// given
		Integer number = 1;
		String someString = "someString";

		// when
		ClassWithoutDefaultConstructor classWithoutDefaultConstructor = aClassWithoutDefaultConstructor(number, someString).build();

		// then
		assertThat(classWithoutDefaultConstructor.getNumber()).isEqualTo(number);
		assertThat(classWithoutDefaultConstructor.getSomeString()).isEqualTo(someString);
	}

}
