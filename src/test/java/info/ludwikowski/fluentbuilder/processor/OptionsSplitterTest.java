/*
 * Created on 17 paź 2014 20:22:46 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.processor;

import static org.fest.assertions.data.MapEntry.entry;

import java.util.Map;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.google.common.collect.Maps;


public class OptionsSplitterTest {

	@Test
	public void shouldSplitWhenOneOfThemContainsNewLineCharacter() {

		//given
		Map<String, String> options = Maps.newHashMap();
		options.put("key1", "value1");
		options.put("key2", "value2\r\n\t\t\t   \t   -Akey3=value3\n\t\t\t  -Akey4=value4");

		//when
		Map<String, String> splitedOptions = OptionsSplitter.splitOptions(options);

		//then
		Assertions.assertThat(splitedOptions).contains(entry("key1", "value1"), entry("key2", "value2"), entry("key3", "value3"), entry("key4", "value4"));
	}
}
