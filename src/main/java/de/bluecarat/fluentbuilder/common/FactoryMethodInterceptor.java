/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.common;

import info.ludwikowski.fluentbuilder.common.AbstractBuilder;
import info.ludwikowski.fluentbuilder.util.NameUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import de.bluecarat.fluentbuilder.annotation.ReferencedField;

/**
 * This class implements a MethodInterceptor. It is used to intercept method
 * calls. Depending on the method name it decides if it should return the
 * finished target object, modify the unfinished target object or select a
 * different constructor.
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 * @param <X> Factory type
 */
public class FactoryMethodInterceptor<X> implements MethodInterceptor {

    private final Class<X> abstractClass;
    private Object targetObject;

    /**
     * Creates a MethodInterceptor which maps factory methods to the builder.
     * @param abstractClass Abstract builder class
     * @param targetObject Target for object creation
     */
    public FactoryMethodInterceptor(final Class<X> abstractClass, final Object targetObject) {
        this.abstractClass = abstractClass;
        this.targetObject = targetObject;
    }

    // CHECKSTYLE IGNORE CyclomaticComplexity|IllegalThrow|NCSS FOR NEXT 3 LINES
    @Override
    public final Object intercept(final Object proxy, final Method method, final Object[] args,
                                  final MethodProxy methodProxy) throws Throwable {
        final String methodName = method.getName();
        if (AbstractBuilder.BUILD_METHOD_NAME.equals(methodName)) {
            return targetObject;
        } else if (AbstractBuilder.ACCESS_TARGET_OBJECT_METHOD_NAME.equals(methodName)) {
            return targetObject;
        } else if (AbstractBuilder.ACCESS_BUILDER_METHOD_NAME.equals(methodName)) {
            return proxy;
        } else if (useProxyForMethod(method, prefixForProxy(abstractClass))) {
            if (method.getParameterTypes().length != 1) {
                throw new UnsupportedOperationException("method should have one parameter");
            }
            invokeSetter(method, args);
            return proxy;
        } else if (methodName.contains(AbstractBuilder.CONSTRUCTOR_METHOD_PREFIX)) {
            final Constructor<? extends Object> matchingConstructor = targetObject.getClass().getConstructor(
                    method.getParameterTypes());
            targetObject = matchingConstructor.newInstance(args);
            return proxy;
        } else {
            // use orignal implementation
            return methodProxy.invokeSuper(proxy, args);
        }
    }

    private void invokeSetter(final Method method, final Object[] args) throws Exception {
        final ReferencedField referencedFieldAnnotation = method.getAnnotation(ReferencedField.class);
        final String annotationValue = referencedFieldAnnotation.value();
        final String fieldName = NameUtils.removePackageNameFromFullyQualifiedName(annotationValue);
        final Class<?> fieldClass = Class.forName(NameUtils.getPackageNameFromFullyQualifiedName(annotationValue));

        setField(targetObject, args, fieldName, fieldClass);

    }

    // Checkstyle ignore rule for the indentation in the method signature which
    // is needed for proper
    // argument alignment.
    // CHECKSTYLE IGNORE indentation FOR NEXT 2 LINES
    private <N> void setField(final N targetObject, final Object[] args, final String fieldName,
                              final Class<?>... superClass) throws NoSuchFieldException, IllegalAccessException {
        Field field = null;
        if (superClass.length == 0) {
            field = targetObject.getClass().getDeclaredField(fieldName);
        } else {
            field = superClass[0].getDeclaredField(fieldName);
        }
        Object value = null;
        if (field.getType() == String.class) {
            value = args[0];
        } else {
            value = args[0];
        }
        field.setAccessible(true);
        field.set(targetObject, value);
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private String prefixForProxy(final Class<X> abstractClass) {
        try {
            final Method method = abstractClass.getMethod("getPrefix");
            return (String) method.invoke(null);
		}
		catch (NoSuchMethodException e) {
            return AbstractBuilder.BUILDER_METHOD_PREFIX;
		}
		catch (SecurityException e) {
        	throw new RuntimeException("Access error, aborting!", e);
        }
		catch (IllegalArgumentException e) {
			throw new RuntimeException("Access error, aborting!", e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException("Access error, aborting!", e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException("Access error, aborting!", e);
		}
    }

    /**
     * Only abstract methods with special prefix can be override by a proxy.
     * @param proxyPrefix
     */
    private boolean useProxyForMethod(final Method method, final String proxyPrefix) {
        return method.getName().startsWith(proxyPrefix) && java.lang.reflect.Modifier.isAbstract(method.getModifiers());
    }

}
