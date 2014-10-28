/*
 * Created on 17 paź 2014 20:18:29 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * For some reasons new line character in <compilerArguments> of maven-processor-plugin causes wrong options parsing.
 * This class repairs this issue.
 * 
 * @author Andrzej Ludwikowski
 */
public class OptionsSplitter {

	private static final String COMPILER_ARGUMENT_PREFIX = "-A";


	public static Map<String, String> splitOptions(Map<String, String> options) {

		Map<String, String> splittedOptions = new HashMap<String, String>();

		for (Entry<String, String> option : options.entrySet()) {
			String optionKey = option.getKey();
			String optionValue = option.getValue();
			if (optionForSplit(optionValue)) {
				splittedOptions.putAll(splitOption(option));
			}
			else {
				splittedOptions.put(optionKey, optionValue);
			}
		}

		return splittedOptions;
	}

	private static Map<String, String> splitOption(Entry<String, String> option) {

		Map<String, String> splittedOptionsMap = new HashMap<String, String>();

		String basicOption = basicOption(option);
		System.out.println(basicOption);
		String[] splittedOptions = basicOption.split(COMPILER_ARGUMENT_PREFIX);
		for (String optionString : splittedOptions) {
			String[] splittedOption = optionString.split("=");
			if (splittedOption.length == 2) {
				splittedOptionsMap.put(removePrefix(splittedOption[0]), splittedOption[1]);
			}
		}
		return splittedOptionsMap;
	}

	private static String removePrefix(String key) {
		return key.replace(COMPILER_ARGUMENT_PREFIX, "");
	}

	private static String basicOption(Entry<String, String> option) {
		return COMPILER_ARGUMENT_PREFIX + option.getKey() + "=" + removeProblematicCharacters(option.getValue());
	}

	private static String removeProblematicCharacters(String optionValue) {
		return optionValue.replaceAll("\n", "").replaceAll("\r", "").replace("\t", "").replaceAll(" ", "");
	}

	private static boolean optionForSplit(String optionValue) {
		return optionValue.contains("\n");
	}

}
