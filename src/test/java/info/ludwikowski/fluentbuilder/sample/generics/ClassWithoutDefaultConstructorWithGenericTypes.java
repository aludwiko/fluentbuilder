package info.ludwikowski.fluentbuilder.sample.generics;

import java.util.List;

import info.ludwikowski.fluentbuilder.annotation.GenerateBuilder;
import info.ludwikowski.fluentbuilder.sample.generics.GenericType;
import info.ludwikowski.fluentbuilder.sample.generics.SimpleType;

@GenerateBuilder
public class ClassWithoutDefaultConstructorWithGenericTypes {

	private Integer number;
	private boolean bool;
	private List<SimpleType> someList;
	private List<GenericType<SimpleType>> someListOfGeneric;

	public ClassWithoutDefaultConstructorWithGenericTypes(Integer number, boolean bool, List<SimpleType> someList, List<GenericType<SimpleType>> someListOfGeneric) {
		this.number = number;
		this.bool = bool;
		this.someList = someList;
		this.someListOfGeneric = someListOfGeneric;
	}

	public Integer getNumber() {
		return number;
	}

	public boolean isBool() {
		return bool;
	}

	public List<SimpleType> getSomeList() {
		return someList;
	}

	public List<GenericType<SimpleType>> getSomeListOfGeneric() {
		return someListOfGeneric;
	}
}
