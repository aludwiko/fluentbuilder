/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.core.GenericTypeResolver;

import de.bluecarat.fluentbuilder.common.FactoryMethodInterceptor;

import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;

import net.sf.cglib.proxy.Enhancer;

/**
 * This class offers static methods for creating bean objects from builder
 * classes.
 * 
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public final class AbstractBuilderFactory {

	private static final int MAX_ARGUMENTS = 100;
	private static final Logger LOGGER = Logger.getLogger(ClassMirrorImpl.class.getName());


	private AbstractBuilderFactory() {

	}

	/**
	 * Create proxy implementation for {@link AbstractBuilder} using custom
	 * target Object, useful for target object without default constructor.
	 * 
	 * @param abstractClass AbstractBuilder class of the targeted class
	 * @param targetObject targeted object
	 * @param <X> AbstractBuilder type
	 * @param <T> targetObject type
	 * @param <B> builder object type
	 * @return Returns a builder object for the targeted class
	 */
	@SuppressWarnings("unchecked")
	public static <X extends AbstractBuilder<T, B>, T, B> X createImplementation(final Class<X> abstractClass,
			final T targetObject) {

		final Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(abstractClass);
		enhancer.setCallback(new FactoryMethodInterceptor<X>(abstractClass, targetObject));
		return (X) enhancer.create();
	}

	/**
	 * Create proxy implementation for {@link AbstractBuilder}. If the target
	 * object has no default constructor, the constructor with the least
	 * arguments is used and invoked with default values.
	 * 
	 * @param abstractBuilderClass AbstractBuilder class of the targeted class
	 * @param <X> AbstractBuilder type
	 * @param <T> targetObject type
	 * @param <B> builder object type
	 * @return Returns a builder object for the targeted class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <X extends AbstractBuilder<T, B>, T, B> X createImplementation(final Class<X> abstractBuilderClass) {

		final Class[] resolveTypeArguments = GenericTypeResolver.resolveTypeArguments(
				abstractBuilderClass,
				AbstractBuilder.class);

		final Class<T> targetObjectClass = resolveTypeArguments[0];
		final T targetObject = (T) createShortestInstance(targetObjectClass);
		return createImplementation(abstractBuilderClass, targetObject);
	}

	// CHECKSTYLE IGNORE CyclomaticComplexity FOR NEXT 1 LINES
	@SuppressWarnings("rawtypes")
	private static Object createShortestInstance(final Class targetObjectClass) {
		final Constructor shortestConstructor = selectShortestConstructor(targetObjectClass);
		final Class[] parameterTypes = shortestConstructor.getParameterTypes();
		final List<Object> emptyParameters = createEmptyParametersFromTypes(parameterTypes);
		final Object[] constructorParameters = emptyParameters.toArray();
		shortestConstructor.setAccessible(true);
		try {
			return shortestConstructor.newInstance(constructorParameters);
		}
		catch (InstantiationException e) {
			LOGGER.severe("Could not create new instance of " + targetObjectClass.getName() + "!");
			return null;
		}
		catch (IllegalAccessException e) {
			LOGGER.severe("Constructor of " + targetObjectClass.getName() + " not accessible!");
			return null;
		}
		catch (IllegalArgumentException e) {
			LOGGER.severe("Illegal argument for constructor found!");
			return null;
		}
		catch (InvocationTargetException e) {
			LOGGER.severe("Could not invoke constructor of " + targetObjectClass.getName());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private static Constructor selectShortestConstructor(final Class targetObjectClass) {
		final Constructor[] constructors = targetObjectClass.getConstructors();
		Constructor shortestConstructor = null;
		int minParameter = MAX_ARGUMENTS;
		for (Constructor constructor : constructors) {
			final int parameterCount = constructor.getParameterTypes().length;
			if (parameterCount < minParameter) {
				shortestConstructor = constructor;
				minParameter = parameterCount;
			}
		}
		return shortestConstructor;
	}

	@SuppressWarnings("rawtypes")
	private static List<Object> createEmptyParametersFromTypes(final Class[] parameterTypes) {
		final List<Object> parameters = new ArrayList<Object>();
		for (Class parameterClass : parameterTypes) {
			parameters.add(getDefaultValueOfClass(parameterClass));
		}
		return parameters;
	}

	private static Object getDefaultValueOfClass(final Class<?> className) {
		if (className.isPrimitive()) {
			return getDefaultValueOfPrimitiveType(className);
		}
		else {
			return null;
		}
	}

	// CHECKSTYLE IGNORE CyclomaticComplexity FOR NEXT 1 LINES
	private static Object getDefaultValueOfPrimitiveType(final Class<?> className) {
		if ("boolean".equals(className.getName())) {
			return false;
		}
		else if ("char".equals(className.getName())) {
			return '\u0000';
		}
		else if ("byte".equals(className.getName())) {
			return Byte.valueOf((byte) 0);
		}
		else if ("short".equals(className.getName())) {
			return Short.valueOf((short) 0);
		}
		else {
			return 0;
		}
	}

}
