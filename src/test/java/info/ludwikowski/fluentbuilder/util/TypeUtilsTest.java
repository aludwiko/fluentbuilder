/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.util;

import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jan van Esdonk
 */
@RunWith(JUnitParamsRunner.class)
public class TypeUtilsTest {

	@SuppressWarnings("unused")
	private static String staticString;
	@SuppressWarnings("unused")
	private final String finalString = "Final";
	@SuppressWarnings("unused")
	private String norStaticNorFinalString;
	@SuppressWarnings("unused")
	private Comparator<String> generic;
	private Field[] fields = TypeUtilsTest.class.getDeclaredFields();


	@Test
	public void shouldCheckThatStaticStringIsStaticOrFinal() {

		// given
		final Field staticStringField = fields[0];

		// when
		boolean result = TypeUtils.isStaticOrFinal(staticStringField);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldCheckThatFinalStringIsStaticOrFinal() {

		// given
		final Field finalStringField = fields[1];

		// when
		boolean result = TypeUtils.isStaticOrFinal(finalStringField);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldCheckThatNorStaticNorFinalStringIsNotStaticOrFinal() {

		// given
		final Field norStaticNorFinalStringField = fields[2];

		// when
		boolean result = TypeUtils.isStaticOrFinal(norStaticNorFinalStringField);

		// then
		assertThat(result).isFalse();
	}

	@Test
	public void shouldReturnTrueForList() {

		// given
		final List<String> testList = new ArrayList<String>();

		// when
		boolean result = TypeUtils.isList(className(testList));

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnFalseForSimpleTypeAndNotList() {

		// given
		final String simple = "test";

		// when
		boolean result = TypeUtils.isList(className(simple));

		// then
		assertThat(result).isFalse();
	}

	@Test
	public void shouldReturnTrueForSet() {

		// given
		final Set<String> testSet = new HashSet<String>();

		// when
		boolean result = TypeUtils.isSet(className(testSet));

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnFalseForSimpleTypeAndNotSet() {

		// given
		final String simple = "test";

		// when
		boolean result = TypeUtils.isSet(className(simple));

		// then
		assertThat(result).isFalse();
	}

	@Test
	@Parameters({ "int", "char", "float", "double", "byte", "short", "long", "boolean" })
	public void shouldReturnTrueForPrimitiveType(String type) {

		// when
		boolean result = TypeUtils.isPrimitiveType(type);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldVerifyThatFieldIsGeneric() {

		// given
		final Field genericField = fields[3];

		// when
		boolean result = TypeUtils.isGeneric(genericField);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldVerifyThatFieldIsNotGeneric() {

		// given
		final Field simpleField = fields[2];

		// when
		boolean result = TypeUtils.isGeneric(simpleField);

		// then
		assertThat(result).isFalse();
	}

	@SuppressWarnings("unused")
	private Object[] packageStringsWithExpectedResults() {
		return $(
				$("info.ludwikowski.example.Order", "Order"),
				$("info.ludwikowski.EXAMPLE.Order", "Order"),
				$("java.util.Map<java.lang.String, info.ludwikowski.EXAMPLE.Order>", "Map<String, Order>"),
				$("java.util1.Map<java.lan3g.String, info.ludwikows3ki.EXAMPLE.Order>", "Map<String, Order>"),
				$("pl.eurobank.MODUL.spisbankow.model.CROdzajUlicy", "CROdzajUlicy"));
	}

	@Test
	@Parameters(method = "packageStringsWithExpectedResults")
	public void shouldRemovePackage(final String packageString, final String expectedResult) {

		// given

		// when
		String result = NameUtils.removePackageNameFromFullyQualifiedName(packageString);

		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	public void shouldReturnPackageFromFQN() {
		final String testFQN = "info.test.software.name";

		final String packageName = NameUtils.getPackageNameFromFullyQualifiedName(testFQN);

		assertThat(packageName).isEqualTo("info.test.software");
	}

	@Test
	public void shouldAddAnForWordStartedWithVowel() {

		// given
		String string = "Order";

		// when
		String result = NameUtils.addIndefiniteArticleInFront(string);

		// then
		assertThat(result).isEqualTo("anOrder");
	}

	@Test
	public void shouldAddAForWordNotStartedWithVowel() {

		// given
		String string = "Bug";

		// when
		String result = NameUtils.addIndefiniteArticleInFront(string);

		// then
		assertThat(result).isEqualTo("aBug");
	}

	@Test
	public void shouldReturnGenericTypeAsString() {
		final String genericString = "Collection<GenericDeclaration>";
		final String type = NameUtils.getOnlyGenericTypeFromGenericDeclaration(genericString);
		assertThat(type).isEqualTo("GenericDeclaration");
	}

	@Test
	public void shouldReturnClassNameOnlyFromGenericTypeDeclarationAsString() {
		final String genericString = "GenericType<SubGenericType<ParametrizedType>";
		final String type = NameUtils.getOnlyClassNameFromGenericDeclaration(genericString);
		assertThat(type).isEqualTo("GenericType");
	}

	private String className(final Object object) {
		return object.getClass().getName();
	}
}
