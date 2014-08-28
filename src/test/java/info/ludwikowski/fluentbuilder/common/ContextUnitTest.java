/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jan van Esdonk
 */
public class ContextUnitTest {

	Context testContext = null;


	@Before
	public void setUp() {
		testContext = new Context();
	}

	@Test
	public void shouldGetMethodPrefix() {
		String defaultValue = "with";
		String returnedValue = testContext.getMethodPrefix();
		assertEquals(defaultValue, returnedValue);
	}

	@Test
	public void shouldSetNewMethodPrefix() {
		String newValue = "newWith";
		testContext.setMethodPrefix(newValue);
		String returnedValue = testContext.getMethodPrefix();
		assertEquals(returnedValue, newValue);
	}

	@Test
	public void shouldGetBuilderClassPostfix() {
		String defaultValue = "Builder";
		String returnedValue = testContext.getBuilderClassPostfix();
		assertEquals(defaultValue, returnedValue);
	}

	@Test
	public void shouldSetNewBuilderClassPostfix() {
		String newValue = "NewBuilder";
		testContext.setBuilderClassPostfix(newValue);
		String returnedValue = testContext.getBuilderClassPostfix();
		assertEquals(newValue, returnedValue);
	}

	@Test
	public void shouldGetAbstractBuilderClassPrefix() {
		String defaultValue = "Abstract";
		String returnedValue = testContext.getAbstractBuilderClassPrefix();
		assertEquals(defaultValue, returnedValue);
	}

	@Test
	public void shouldSetAbstractBuilderClassPrefix() {
		String newValue = "NewAbstract";
		testContext.setAbstractBuilderClassPrefix(newValue);
		String returnedValue = testContext.getAbstractBuilderClassPrefix();
		assertEquals(newValue, returnedValue);
	}

	@Test
	public void shouldGetStaticCreateMethodName() {
		String returnedValue = testContext.getStaticCreateMethodName();
		assertEquals(null, returnedValue);
	}

	@Test
	public void shouldSetStaticCreateMethodName() {
		String newValue = "testName";
		testContext.setStaticCreateMethodName(newValue);
		String returnedValue = testContext.getStaticCreateMethodName();
		assertEquals(newValue, returnedValue);
	}
}
