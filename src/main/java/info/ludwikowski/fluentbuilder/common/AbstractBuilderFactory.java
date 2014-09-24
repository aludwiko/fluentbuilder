/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.logging.Logger;

import net.sf.cglib.proxy.Enhancer;

import org.springframework.core.GenericTypeResolver;

/**
 * This class offers static methods for creating bean objects from builder
 * classes.
 * 
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public final class AbstractBuilderFactory {

	private static final Logger LOGGER = Logger.getLogger(AbstractBuilderFactory.class.getName());


	private AbstractBuilderFactory() {}


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
	 * Create proxy implementation for {@link AbstractBuilder}. Method required default constructor in <T>
	 * 
	 * @param abstractBuilderClass AbstractBuilder class of the targeted class
	 * @param <X> AbstractBuilder type
	 * @param <T> targetObject type
	 * @param <B> builder object type
	 * @return Returns a builder object for the targeted class
	 */
	public static <X extends AbstractBuilder<T, B>, T, B> X createImplementation(final Class<X> abstractBuilderClass) {

		return createImplementation(abstractBuilderClass, new Object[] {});
	}

	/**
	 * Create proxy implementation for {@link AbstractBuilder}.
	 * 
	 * @param abstractBuilderClass AbstractBuilder class of the targeted class
	 * @param constructorParams - params for invoking not default constructor
	 * @param <X> AbstractBuilder type
	 * @param <T> targetObject type
	 * @param <B> builder object type
	 * @return Returns a builder object for the targeted class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <X extends AbstractBuilder<T, B>, T, B> X createImplementation(final Class<X> abstractBuilderClass, Object... constructorParams) {

		final Class[] resolveTypeArguments = GenericTypeResolver.resolveTypeArguments(
				abstractBuilderClass,
				AbstractBuilder.class);

		final Class<T> targetObjectClass = resolveTypeArguments[0];
		final T targetObject = createInstance(targetObjectClass, constructorParams);
		return createImplementation(abstractBuilderClass, targetObject);
	}


	/**
	 * FIXME remove shortest constructor because it might be dagerous to use.
	 * 
	 * @param <T>
	 * 
	 * @param targetObjectClass
	 * @param costructorParams
	 * @return
	 */
	// CHECKSTYLE IGNORE CyclomaticComplexity FOR NEXT 1 LINES
	private static <T> T createInstance(final Class<T> targetObjectClass, Object... constructorParams) {

		try {
			Constructor<T> constructor = findProperConstructor(targetObjectClass, constructorParams);
			constructor.setAccessible(true);
			return constructor.newInstance(constructorParams);
		}
		catch (InstantiationException e) {
			LOGGER.severe("Could not create new instance of " + targetObjectClass.getName() + "! " + e.getMessage());
			return null;
		}
		catch (IllegalAccessException e) {
			LOGGER.severe("Constructor of " + targetObjectClass.getName() + " not accessible! " + e.getMessage());
			return null;
		}
		catch (IllegalArgumentException e) {
			LOGGER.severe("Illegal argument for constructor found! " + e.getMessage());
			return null;
		}
		catch (InvocationTargetException e) {
			LOGGER.severe("Could not invoke constructor of " + targetObjectClass.getName() + " " + e.getMessage());
			return null;
		}
		catch (SecurityException e) {
			LOGGER.severe("Could not find proper construcotr of " + targetObjectClass.getName() + " " + e.getMessage());
			return null;
		}
		catch (NoSuchMethodException e) {
			LOGGER.severe("Could not find proper construcotr of " + targetObjectClass.getName() + " " + e.getMessage());
			return null;
		}
		catch (IllegalStateException e) {
			LOGGER.severe("Could not find proper construcotr of " + targetObjectClass.getName() + " " + e.getMessage());
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	private static <T> Constructor<T> findProperConstructor(final Class<T> targetObjectClass, Object... constructorParams) throws SecurityException, NoSuchMethodException {
		if (constructorParams.length == 0) {
			return targetObjectClass.getDeclaredConstructor();
		}

		for (Constructor<?> constructor : targetObjectClass.getDeclaredConstructors()) {
			if (paramsMatch(constructor.getParameterTypes(), constructorParams)) {
				return (Constructor<T>) constructor;
			}
		}
		throw new IllegalStateException("No constructor found for " + targetObjectClass + " with params " + Arrays.toString(constructorParams));
	}

	private static boolean paramsMatch(Class<?>[] parameterTypes, Object[] constructorParams) {

		if (parameterTypes.length != constructorParams.length) {
			return false;
		}
		for (int i = 0; i < parameterTypes.length; i++) {
			if (!constructorParams[i].getClass().isAssignableFrom(parameterTypes[i])) {
				return false;
			}

		}
		return true;
	}
}
