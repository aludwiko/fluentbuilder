/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.fluentbuilder.generator;

import static java.nio.charset.StandardCharsets.UTF_8;
import info.ludwikowski.fluentbuilder.common.AbstractBuilderPrinter;
import info.ludwikowski.fluentbuilder.common.Context;
import info.ludwikowski.fluentbuilder.common.OldBuilderPrinter;
import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class AbstractBuilderGenerator {

	private Class<?> clazz;
	private Context context = new Context();
	private PrintStream printStream;


	private AbstractBuilderGenerator(Class<?> clazz, PrintStream printStream) {
		this.clazz = clazz;
		this.printStream = printStream;
	}

	/**
	 * 
	 * @param clazz - class for builder generation
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static AbstractBuilderGenerator forClass(Class<?> clazz) throws UnsupportedEncodingException {
		return new AbstractBuilderGenerator(clazz, new PrintStream(System.out, false, UTF_8.name()));
	}

	/**
	 * 
	 * @param clazz - class for builder generation
	 * @return
	 */
	public static AbstractBuilderGenerator forClassWithWriter(Class<?> clazz, PrintStream printStream) {
		return new AbstractBuilderGenerator(clazz, printStream);
	}

	/**
	 * Default generated method name prefix is 'with', e.g. for field id method name will be 'withId(long id)';
	 * 
	 * @param methodPrefix - new method prefix
	 * @return
	 */
	public AbstractBuilderGenerator withMethodPrefix(String methodPrefix) {
		context.setMethodPrefix(methodPrefix);
		return this;
	}

	/**
	 * Default is <code>true<code>
	 * 
	 * @param staticCreate
	 * @return
	 */
	public AbstractBuilderGenerator withStaticCreate(boolean staticCreate) {
		context.setStaticCreate(staticCreate);
		return this;
	}

	/**
	 * Default is <code>true<code>
	 * 
	 * @param varargsForCollections
	 * @return
	 */
	public AbstractBuilderGenerator withVarArgsForCollections(boolean varArgsForCollections) {
		context.setVarArgsForCollections(varArgsForCollections);
		return this;
	}

	/**
	 * Default is Builder
	 * 
	 * @param builderNamePostfix - new builder klass name posfix
	 * @return
	 */
	public AbstractBuilderGenerator withBuilderClassPostfix(String builderClassPostfix) {
		context.setBuilderClassPostfix(builderClassPostfix);
		return this;
	}

	/**
	 * Default is Abstract
	 * 
	 * @param abstractBuilderClassPrefix - new abstract builder class name prefix
	 * @return
	 */
	public AbstractBuilderGenerator withAbstractBuilderClassPrefix(String abstractBuilderClassPrefix) {
		context.setAbstractBuilderClassPrefix(abstractBuilderClassPrefix);
		return this;
	}

	/**
	 * Default is "create"
	 * 
	 * @param staticCreateMethodName - custom create method name
	 * @return
	 */
	public AbstractBuilderGenerator withStaticCreateMethodName(String staticCreateMethodName) {
		context.setStaticCreateMethodName(staticCreateMethodName);
		return this;
	}

	/**
	 * Default is "build"
	 * 
	 * @param buildMethodName - custom build method name
	 * @return
	 */
	public AbstractBuilderGenerator withBuildMethodName(String buildMethodName) {
		context.setBuildMethodName(buildMethodName);
		return this;
	}

	/**
	 * If classes has some irrational prefix like 'C', builder can ignore this prefix
	 * 
	 * @param ignoredClassPostfix
	 * @return
	 */
	public AbstractBuilderGenerator withIgnoredClassPrefix(String ignoredClassPrefix) {
		context.setIgnoredClassPrefix(ignoredClassPrefix);
		return this;
	}

	public AbstractBuilderGenerator withIndefiniteArticles(boolean indefiniteArticles) {
		context.setUseIndefiniteArticles(indefiniteArticles);
		return this;
	}

	/**
	 * print two builders to {@link System#out}
	 */
	public void printBuilders() {

		ClassMirror classMirror = new ClassMirrorImpl(clazz, context);
		AbstractBuilderPrinter abstractBuilderPrinter = new AbstractBuilderPrinter(classMirror, context);
		OldBuilderPrinter builderPrinter = new OldBuilderPrinter(classMirror, context);

		printStream.println(abstractBuilderPrinter.printClass());
		printStream.println("/////////////////////////////////////");
		printStream.println(builderPrinter.printClass());
	}

	/**
	 * Print single builder. Combine methods from two separate builder.
	 */
	public void printSingleBuilder() {

		ClassMirror classMirror = new ClassMirrorImpl(clazz, context);
		// System.out.println(new SingleBuilderPrinter(classMirror, context).printClass());
	}
}
