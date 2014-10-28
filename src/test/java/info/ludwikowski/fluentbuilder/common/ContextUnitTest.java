/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

/**
 * @author Jan van Esdonk
 */
public class ContextUnitTest {

	Context context = new Context();


	@Test
	public void should_split_regexps() {

		// given
		context.setIgnoreFields("_.*;id;version");

		// when
		List<String> result = context.getIgnoreFieldsRegexps();

		// then
		assertThat(result).containsOnly("_.*", "id", "version");
	}
}
