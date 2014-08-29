/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.lang.reflect.Method;

import org.junit.Test;

import de.bluecarat.fluentbuilder.samples.AbstractDoubleExtendsTestObjectBuilder;
import de.bluecarat.fluentbuilder.samples.AbstractExtendsTestObjectBuilder;
import de.bluecarat.fluentbuilder.samples.BaseTestObject;
import de.bluecarat.fluentbuilder.samples.BaseTestObjectBuilder;
import de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject;
import de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObjectBuilder;
import de.bluecarat.fluentbuilder.samples.ExtendsTestObject;
import de.bluecarat.fluentbuilder.samples.ExtendsTestObjectBuilder;

/**
 * @author Jan van Esdonk
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FactoryMethodInterceptorUnitTest {

	@Test
	public void shouldReturnBean() throws Throwable {
		// given
		final Class builderClass = BaseTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);

		// when
		final Method buildMethod = AbstractBuilder.class.getMethod("build");
		final Object testObject = testInterceptor.intercept(testBuildObject, buildMethod, null, null);

		// then
		assertThat(testObject, is(instanceOf(BaseTestObject.class)));
	}

	@Test
	public void shouldSetFieldFromBaseClassInBaseClassBasedOnMethodName() throws Throwable {
		// given
		final Class builderClass = BaseTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withIntField");
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

	@Test
	public void shouldSetFieldInSuperClassBasedOnMethodName() throws Throwable {
		// given
		final Class builderClass = ExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withBaseTestObjectSameField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final ExtendsTestObject testObject = (ExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedFieldfromBase(), is(1));
	}

	@Test
	public void shouldSetFieldInSuperSuperClassBasedOnMethodName() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withBaseTestObjectSameField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedFieldfromBase(), is(1));
	}

	@Test
	public void shouldSetInheritatedButNotDuplicatedField() throws Throwable {
		// given
		final Class builderClass = ExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withUniqueBaseField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final ExtendsTestObject testObject = (ExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getUniqueBaseField(), is(1));
	}

	@Test
	public void shouldSetFieldWhichIsDuplicatedInSuperSuperClassButNotInSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withDuplicateInSuperSuperAndBase");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedInSuperSuperAndBaseFromBase(), is(1));
	}

	@Test
	public void shouldSetSuperSuperFieldWhichIsDuplicatedInSuperSuperClassButNotInSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withBaseTestObjectDuplicateInSuperSuperAndBase");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedInSuperSuperAndBaseFromSuperSuper(), is(1));
	}

	@Test
	public void shouldSetFieldWhichIsDuplicatedInSuperClassButNotInSuperSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withDuplicateInSuperAndBase");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedInSuperAndBaseFromBase(), is(1));
	}

	@Test
	public void shouldSetSuperFieldWhichIsDuplicatedInSuperClassButNotInSuperSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withExtendsTestObjectDuplicateInSuperAndBase");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedInSuperAndBaseFromSuper(), is(1));
	}

	@Test
	public void shouldSetFieldWhichIsDuplicatedInSuperClassAndSuperSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withSameField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedFieldFromDoubleExtendingClass(), is(1));
	}

	@Test
	public void shouldSetSuperFieldWhichIsDuplicatedInSuperClassAndSuperSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withExtendsTestObjectSameField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedFieldFromSingleExtendingClass(), is(1));
	}

	@Test
	public void shouldSetSuperSuperFieldWhichIsDuplicatedInSuperClassAndSuperSuperClass() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withBaseTestObjectSameField");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedFieldfromBase(), is(1));
	}

	@Test
	public void shouldSetSuperFieldWhichIsDuplicatedInSuperClassAndSuperSuperClassButNotInTheExtendingClass()
			throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withDuplicateInSuperSuperAndSuper");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedInSuperSuperAndSuperFromSuper(), is(1));
	}

	@Test
	public void shouldSetSuperSuperFieldWhichIsDuplicatedInSuperClassAndSuperSuperClassButNotInTheExtendingClass()
			throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withBaseTestObjectDuplicateInSuperSuperAndSuper");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getDuplicatedInSuperSuperAndSuperFromSuperSuper(), is(1));
	}

	private Method getMethodWithNameFromDoubleExtendingObject(final String name) {
		Method[] methods = AbstractDoubleExtendsTestObjectBuilder.class.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

	@Test
	public void shouldSetFieldInSuperSuperClassWhichIsDuplicatedInSuperClass() throws Throwable {
		// given
		final Class builderClass = ExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromExtendingObject("withBaseTestObjectBaseTestObjectNameConflict");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final ExtendsTestObject testObject = (ExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getBaseTestObjectNameConflictFromSuper(), is(1));
	}

	private Method getMethodWithNameFromExtendingObject(final String name) {
		Method[] methods = AbstractExtendsTestObjectBuilder.class.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

	@Test
	public void shouldUseAnnotationToSetFieldValueAndIgnoreMethodName() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("withRandomMethodName2131");
		final Object[] args = new Object[] { Integer.valueOf(1) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getRandomNameField(), is(1));
	}

	@Test
	public void shouldCreateObjectWithMatchingConstructor() throws Throwable {
		// given
		final Class builderClass = DoubleExtendsTestObjectBuilder.class;
		final Object testBuildObject = AbstractBuilderFactory.createImplementation(builderClass).build();
		final FactoryMethodInterceptor testInterceptor = new FactoryMethodInterceptor(builderClass, testBuildObject);
		final Method method = getMethodWithNameFromDoubleExtendingObject("constructedWithFirstParameterAndSecondParameter");
		final Object[] args = new Object[] { Integer.valueOf(666), Integer.valueOf(888) };

		// when
		final DoubleExtendsTestObject testObject = (DoubleExtendsTestObject) testInterceptor.intercept(
				testBuildObject,
				method,
				args,
				null);

		// then
		assertThat(testObject.getStringField(), containsString("constructedWith executed"));
	}

}
