/*
 * Created on 19-03-2013 17:03:58 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils {

	private static final String A = "a";
	private static final String AN = "an";
	private static final String PACKAGE_REGEXP = "([a-z]*\\.)";
	private static final String IMPORT_STATEMENT_REGEXP = "([a-zA-Z_]{1}[a-zA-Z0-9_]*(\\.[a-zA-Z_]{1}[a-zA-Z0-9_]*)*)";
	public static Pattern importStatementPattern = Pattern.compile(IMPORT_STATEMENT_REGEXP);
	public static final String EMPTY = "";
	public static final List<Character> VOWELS = new ArrayList<Character>();

	static {
		VOWELS.add('a');
		VOWELS.add('e');
		VOWELS.add('i');
		VOWELS.add('o');
		VOWELS.add('u');
	}

	public static String removePackage(String value) {
		return value.replaceAll(PACKAGE_REGEXP, "");
	}

	public static boolean hasText(String staticCreateMethodName) {
		return staticCreateMethodName != null && !staticCreateMethodName.trim().equals("");
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

	public static String repeat(String string, int howManyTimes) {

		if (howManyTimes == 0) {
			return EMPTY;
		}

		if (howManyTimes == 1) {
			return string;
		}

		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < howManyTimes; i++) {
			stringBuilder.append(string);
		}

		return stringBuilder.toString();
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

	public static Object nullToEmpty(String string) {

		if (string == null) {
			return EMPTY;
		}
		return string;
	}

	public static String addIndefineArticle(String string) {

		char firstChar = Character.toLowerCase(string.charAt(0));

		if (VOWELS.contains(firstChar)) {
			return AN + string;
		}
		else {
			return A + string;
		}
	}

}
