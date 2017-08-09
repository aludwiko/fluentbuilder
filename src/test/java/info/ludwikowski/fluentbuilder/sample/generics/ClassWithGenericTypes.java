package info.ludwikowski.fluentbuilder.sample.generics;

import java.util.List;

import info.ludwikowski.fluentbuilder.annotation.GenerateBuilder;
import info.ludwikowski.fluentbuilder.sample.generics.GenericType;
import info.ludwikowski.fluentbuilder.sample.generics.SimpleType;

@GenerateBuilder
public class ClassWithGenericTypes {

	private List<SimpleType> someList;
	private List<GenericType<SimpleType>> someListOfGeneric;

	public List<SimpleType> getSomeList() {
		return someList;
	}

	public void setSomeList(List<SimpleType> someList) {
		this.someList = someList;
	}

	public List<GenericType<SimpleType>> getSomeListOfGeneric() {
		return someListOfGeneric;
	}

	public void setSomeListOfGeneric(List<GenericType<SimpleType>> someListOfGeneric) {
		this.someListOfGeneric = someListOfGeneric;
	}
}
