/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @author Jan van Esdonk
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FactoryMethodInterceptorUnitTest {

	@Test
	public void shouldReturnTargetObject() throws Throwable {
		// given
		final Class builderClass = BaseTestObjectBuilder.class;
		final Object testBuildObject = new BaseTestObject();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);

		// when
		final Method buildMethod = AbstractBuilder.class.getMethod("build");
		final Object result = testInterceptor.intercept(testBuildObject, buildMethod, null, null);

		// then
		assertThat(result).isEqualTo(testBuildObject);
	}

	@Test
	public void shouldSetFieldFromBaseClassInBaseClassBasedOnMethodName() throws Throwable {
		// given
		final Class builderClass = BaseTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject(BaseTestObjectBuilder.class, "withIntField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final BaseTestObject testObject = (BaseTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getIntField(), is(1));
	}

	private Method getMethodWithNameFromDoubleExtendingObject(Class<BaseTestObjectBuilder> clazz, final String name) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}


}
