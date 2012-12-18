package abstractfluentbuilder;

import static abstractfluentbuilder.AbstractBuilder.ACCESS_BUILDER_METHOD_NAME;
import static abstractfluentbuilder.AbstractBuilder.ACCESS_TARGET_OBJECT_METHOD_NAME;
import static abstractfluentbuilder.AbstractBuilder.BUILD_METHOD_NAME;
import static org.apache.commons.lang.StringUtils.uncapitalize;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.springframework.core.GenericTypeResolver;


public class AbstractBuilderFactory {

	private static final String PREFIX_WITH = "with";

	@SuppressWarnings("unchecked")
	public static <X extends AbstractBuilder<T, B>, T, B> X createDefaultImplementation(Class<X> abstractClass, final T targetObject) {

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
				else if (methodName.startsWith(PREFIX_WITH)) {

					if (method.getParameterTypes().length != 1) {
						throw new UnsupportedOperationException();
					}
					String fieldName = uncapitalize(methodName.substring(PREFIX_WITH.length()));

					final Field field = targetObject.getClass().getDeclaredField(fieldName);

					field.setAccessible(true);

					field.set(targetObject, field.getType() == String.class ? (String) args[0] : args[0]);

					return proxy;
				}

				else {
					return methodProxy.invokeSuper(proxy, args);
				}
			}
		});
		return (X) enhancer.create();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <X extends AbstractBuilder<T, B>, T, B> X createDefaultImplementation(Class<X> abstractBuilderClass) {

		Class[] resolveTypeArguments = GenericTypeResolver.resolveTypeArguments(abstractBuilderClass, AbstractBuilder.class);

		T targetObject;
		try {
			targetObject = (T) resolveTypeArguments[0].newInstance();
			return createDefaultImplementation(abstractBuilderClass, targetObject);
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new IllegalStateException("cos nie tak");
	}
}