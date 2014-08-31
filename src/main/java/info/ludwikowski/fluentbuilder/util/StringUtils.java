/*
 * Created on 31 sie 2014 21:30:40 by Andrzej
 */

package info.ludwikowski.fluentbuilder.util;


public class StringUtils {

	public static final String EMPTY = "";


	/**
	 * Concatenates a String n-Times with itself.
	 * 
	 * @param string String to repeat
	 * @param howManyTimes number of repetitions
	 * @return the concatenated String
	 */
	public static String repeat(final String string, final int howManyTimes) {
		final StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < howManyTimes; i++) {
			stringBuilder.append(string);
		}

		return stringBuilder.toString();
	}

	/**
	 * @return true only if text is not null and is not empty after trim
	 */
	public static boolean hasText(String text) {
		return text != null && !text.trim().equals("");
	}

	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0)))
										.append(str.substring(1))
										.toString();
	}

	public static String uncapitalize(String str) {

		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0)))
										.append(str.substring(1))
										.toString();
	}

}
