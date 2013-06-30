package info.ludwikowski.util;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;


public class StringUtilsUnitTest {

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
