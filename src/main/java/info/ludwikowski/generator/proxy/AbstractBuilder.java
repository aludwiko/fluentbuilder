/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.generator.proxy;


public abstract class AbstractBuilder<T, B> {

	public static final String ACCESS_TARGET_OBJECT_METHOD_NAME = "targetObject";
	public static final String ACCESS_BUILDER_METHOD_NAME = "builder";
	public static final String BUILD_METHOD_NAME = "build";


	/**
	 * return target object for further modification, e.g. invoking domain methods 
	 */
	protected abstract T targetObject();

	/**
	 * return current builder
	 */
	protected abstract B builder();

	/**
	 * build target object
	 */
	public abstract T build();
}
