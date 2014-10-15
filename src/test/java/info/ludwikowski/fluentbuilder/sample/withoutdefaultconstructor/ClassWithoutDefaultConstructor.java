/*
 * Created on 3 wrz 2014 21:00:39 by Andrzej
 */

package info.ludwikowski.fluentbuilder.sample.withoutdefaultconstructor;

import info.ludwikowski.fluentbuilder.annotation.GenerateBuilder;

@GenerateBuilder
public class ClassWithoutDefaultConstructor {

	private Integer number;
	private String someString;


	public ClassWithoutDefaultConstructor(Integer number) {
		this.number = number;
	}

	public ClassWithoutDefaultConstructor(String someString) {
		this.someString = someString;
	}

	public ClassWithoutDefaultConstructor(Integer number, String someString) {
		this.number = number;
		this.someString = someString;
	}

	public Integer getNumber() {
		return number;
	}

	public String getSomeString() {
		return someString;
	}
}
