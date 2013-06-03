/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.generator.proxy;

import static org.springframework.util.StringUtils.hasText;
import info.ludwikowski.common.FluentBuilderFieldProvider;

public class AbstractBuilderGenerator {

	private Class<?> clazz;
	private String methodPrefix = "with";
	private String builderNamePostfix = "Builder";
	private String staticCreateMethodName;
	private String buildMethodName;
	private AbstractBuilderPrinter printer = new AbstractBuilderPrinter(System.out);
	private FluentBuilderFieldProvider fieldProvider = new FluentBuilderFieldProvider();
	private boolean staticCreate = true;
	private boolean varargsForCollections = true;


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
		this.methodPrefix = methodPrefix;
		return this;
	}

	public AbstractBuilderGenerator withStaticCreate(boolean staticCreate) {
		this.staticCreate = staticCreate;
		return this;
	}

	public AbstractBuilderGenerator withVarargsForCollections(boolean varargsForCollections) {
		this.varargsForCollections = varargsForCollections;
		return this;
	}

	/**
	 * default is Builder
	 * 
	 * @param builderNamePostfix - new builder name posfix
	 * @return
	 */
	public AbstractBuilderGenerator withBuilderNamePostfix(String builderNamePostfix) {
		this.builderNamePostfix = builderNamePostfix;
		return this;
	}

	/**
	 * @param staticCreateMethodName - custom create method name
	 * @return
	 */
	public AbstractBuilderGenerator withStaticCreateMethodName(String staticCreateMethodName) {
		this.staticCreateMethodName = staticCreateMethodName;
		return this;
	}

	/**
	 * @param buildMethodName - custom build method name
	 * @return
	 */
	public AbstractBuilderGenerator withBuildMethodName(String buildMethodName) {
		this.buildMethodName = buildMethodName;
		return this;
	}

	/**
	 * print builder to {@link System#out}
	 */
	public void printBuilder() {

		String className = clazz.getSimpleName();
		String realBuilderName = builderName(className);

		printer.printComment(className);
		printer.printBuilderBegin(className, realBuilderName);

		if (staticCreate) {
			printer.printCreateMethod(realBuilderName, staticCreateMethodName);
		}

		printer.printGetPrefixMethod(methodPrefix);

		if (hasText(buildMethodName)) {
			printer.printBuildMethod(className, buildMethodName);
		}

		printer.printBuilderBody(fieldProvider.findProperFields(clazz), realBuilderName, methodPrefix);

		if (varargsForCollections) {
			printer.printVarargsMethods(fieldProvider.findOnlyCollectionFields(clazz), realBuilderName, methodPrefix);
		}

		printer.printBuilderEnd();
	}

	private String builderName(String className) {
		return className + builderNamePostfix;
	}

}
