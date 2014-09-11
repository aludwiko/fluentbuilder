/*
 * Created on 11 wrz 2014 20:45:39 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample;

import java.math.BigDecimal;


public class ClassWithDefaultConstructor {

	private Integer number;
	private BigDecimal classFormAnotherPackage;


	@SuppressWarnings("unused")
	private ClassWithDefaultConstructor() {}

	ClassWithDefaultConstructor(Integer number) {
		this.number = number;
	}

	protected ClassWithDefaultConstructor(BigDecimal classFormAnotherPackage) {
		this.classFormAnotherPackage = classFormAnotherPackage;
	}

	public ClassWithDefaultConstructor(Integer number, BigDecimal classFormAnotherPackage) {
		this.number = number;
		this.classFormAnotherPackage = classFormAnotherPackage;
	}

	public Integer getNumber() {
		return number;
	}

	public BigDecimal getClassFormAnotherPackage() {
		return classFormAnotherPackage;
	}
}
