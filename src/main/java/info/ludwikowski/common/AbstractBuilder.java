/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.common;

/**
 * 
 * @author andrzej.ludwikowski
 * 
 * @param <T> target object type
 * @param <B> builder type
 */
public abstract class AbstractBuilder<T, B> {

	public static final String ACCESS_TARGET_OBJECT_METHOD_NAME = "targetObject";
	public static final String ACCESS_BUILDER_METHOD_NAME = "builder";
	public static final String BUILD_METHOD_NAME = "build";
	public static final String BUILDER_METHOD_PREFIX = "with";


	/**
	 * return target object for further modification, useful for invoking domain methods
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
