/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

/**
 * This abstract class provides an interface for generating AbstractBuilder
 * classes for each processed class.
 * 
 * @author Andrzej Ludwikowski
 * @param <T> target object type
 * @param <B> builder type
 */
public abstract class AbstractBuilder<T, B> {

	public static final String ACCESS_TARGET_OBJECT_METHOD_NAME = "targetObject";
	public static final String ACCESS_BUILDER_METHOD_NAME = "builder";
	public static final String BUILD_METHOD_NAME = "build";
	public static final String BUILDER_METHOD_PREFIX = "with";
	public static final String CONSTRUCTOR_METHOD_PREFIX = "constructedWith";


	/**
	 * return target object for further modification, useful for invoking domain
	 * methods.
	 * 
	 * @return T the current target object in its current state
	 */
	protected abstract T targetObject();

	/**
	 * Returns the current builder object.
	 * 
	 * @return B the current builder object.
	 */
	protected abstract B builder();

	/**
	 * build target object.
	 * 
	 * @return T the created bean
	 */
	public abstract T build();
}
