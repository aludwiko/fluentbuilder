/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.generator.proxy;

import static info.ludwikowski.generator.proxy.AbstractBuilder.ACCESS_BUILDER_METHOD_NAME;
import static info.ludwikowski.generator.proxy.AbstractBuilder.ACCESS_TARGET_OBJECT_METHOD_NAME;
import static info.ludwikowski.generator.proxy.AbstractBuilder.BUILD_METHOD_NAME;
import static java.lang.reflect.Modifier.isAbstract;
import static org.apache.commons.lang.StringUtils.uncapitalize;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.springframework.core.GenericTypeResolver;


public class AbstractBuilderFactory {

	private static final String PREFIX_WITH = "with";

	@SuppressWarnings("unchecked")
	public static <X extends AbstractBuilder<T, B>, T, B> X createImplementation(Class<X> abstractClass, final T targetObject) {

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(abstractClass);
		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

				String methodName = method.getName();

				if (BUILD_METHOD_NAME.equals(methodName)) {
					return targetObject;
				}
				else if (ACCESS_TARGET_OBJECT_METHOD_NAME.equals(methodName)) {
					return targetObject;
				}
				else if (ACCESS_BUILDER_METHOD_NAME.equals(methodName)) {
					return proxy;
				}
				else if (useProxyForMethod(method)) {

					if (method.getParameterTypes().length != 1) {
						throw new UnsupportedOperationException("method should have one parameter");
					}

					String fieldName = uncapitalize(methodName.substring(PREFIX_WITH.length()));

					setField(targetObject, args, fieldName);

					return proxy;
				}

				else {
					// use orignal implementation
					return methodProxy.invokeSuper(proxy, args);
				}
			}

			private <N> void setField(final N targetObject, Object[] args, String fieldName) throws NoSuchFieldException, IllegalAccessException {

				final Field field = targetObject.getClass().getDeclaredField(fieldName);

				field.setAccessible(true);

				field.set(targetObject, field.getType() == String.class ? (String) args[0] : args[0]);
			}

			/**
			 * only abstract methods  with special prefix can be override by proxy
			 */
			private boolean useProxyForMethod(Method method) {

				return method.getName().startsWith(PREFIX_WITH) && isAbstract(method.getModifiers());
			}
		});
		return (X) enhancer.create();
	}

	/**
	 * requires that target object has default constructor (can be private)
	 * @param abstractBuilderClass
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <X extends AbstractBuilder<T, B>, T, B> X createImplementation(Class<X> abstractBuilderClass) {

		Class[] resolveTypeArguments = GenericTypeResolver.resolveTypeArguments(abstractBuilderClass, AbstractBuilder.class);

		try {
			Class<T> targetObjectClass = resolveTypeArguments[0];
			Constructor<T> defualtConstructor = targetObjectClass.getDeclaredConstructor();
			defualtConstructor.setAccessible(true);
			T targetObject = defualtConstructor.newInstance();
			return createImplementation(abstractBuilderClass, targetObject);
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException(
				"something wrong happened, make sure that your target class has default constructor, if it already has it try tu use method: createImplementation(Class<X> abstractClass, final T targetObject)");
	}
}