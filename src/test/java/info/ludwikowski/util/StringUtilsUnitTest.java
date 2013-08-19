package info.ludwikowski.util;

import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.api.Assertions.assertThat;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringUtilsUnitTest {

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
	public void shouldRemovePackage(String packageString, String expectedResult) {

		// given

		// when
		String result = StringUtils.removePackage(packageString);

		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	public void shouldAddAnForWordStartedWithVowel() {

		// given
		String string = "Order";

		// when
		String result = StringUtils.addIndefineArticle(string);

		// then
		assertThat(result).isEqualTo("anOrder");
	}

	@Test
	public void shouldAddAForWordNotStartedWithVowel() {

		// given
		String string = "Bug";

		// when
		String result = StringUtils.addIndefineArticle(string);

		// then
		assertThat(result).isEqualTo("aBug");
	}
}
