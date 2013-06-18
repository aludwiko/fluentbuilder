/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.generator;

import info.ludwikowski.common.AbstractBuilderPrinter;
import info.ludwikowski.common.BuilderPrinter;
import info.ludwikowski.common.Context;
import info.ludwikowski.common.SingleBuilderPrinter;
import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.model.ClassMirrorImpl;

public class AbstractBuilderGenerator {

	private Class<?> clazz;
	private Context context = new Context();


	private AbstractBuilderGenerator(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 
	 * @param clazz - class for builder generation
	 * @return
	 */
	public static AbstractBuilderGenerator forClass(Class<?> clazz) {
		return new AbstractBuilderGenerator(clazz);
	}

	/**
	 * default generated method name prefix is 'with', e.g. for field id method name will be 'withId(long id)';
	 * 
	 * @param methodPrefix - new method prefix
	 * @return
	 */
	public AbstractBuilderGenerator withMethodPrefix(String methodPrefix) {
		context.setMethodPrefix(methodPrefix);
		return this;
	}

	public AbstractBuilderGenerator withStaticCreate(boolean staticCreate) {
		context.setStaticCreate(staticCreate);
		return this;
	}

	public AbstractBuilderGenerator withVarargsForCollections(boolean varargsForCollections) {
		context.setVarargsForCollections(varargsForCollections);
		return this;
	}

	/**
	 * default is Builder
	 * 
	 * @param builderNamePostfix - new builder name posfix
	 * @return
	 */
	public AbstractBuilderGenerator withBuilderClassPostfix(String builderClassPostfix) {
		context.setBuilderClassPostfix(builderClassPostfix);
		return this;
	}

	/**
	 * @param staticCreateMethodName - custom create method name
	 * @return
	 */
	public AbstractBuilderGenerator withStaticCreateMethodName(String staticCreateMethodName) {
		context.setStaticCreateMethodName(staticCreateMethodName);
		return this;
	}

	/**
	 * @param buildMethodName - custom build method name
	 * @return
	 */
	public AbstractBuilderGenerator withBuildMethodName(String buildMethodName) {
		context.setBuildMethodName(buildMethodName);
		return this;
	}

	/**
	 * print two builders to {@link System#out}
	 */
	public void printBuilders() {

		ClassMirror classMirror = new ClassMirrorImpl(clazz, context);
		AbstractBuilderPrinter abstractBuilderPrinter = new AbstractBuilderPrinter(context, classMirror);
		BuilderPrinter builderPrinter = new BuilderPrinter(classMirror, context);

		System.out.println(abstractBuilderPrinter.printClass());
		System.out.println("/////////////////////////////////////");
		System.out.println(builderPrinter.printClass());

	}

	/**
	 * Print single builder. Combine methods from two separate builder.
	 */
	public void printSingleBuilder() {

		ClassMirror classMirror = new ClassMirrorImpl(clazz, context);
		System.out.println(new SingleBuilderPrinter(context, classMirror).printClass());
	}
}
