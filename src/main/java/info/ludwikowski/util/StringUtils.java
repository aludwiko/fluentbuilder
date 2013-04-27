/*
 * Created on 19-03-2013 17:03:58 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static final String PACKAGE_REGEXP = "([a-z]*\\.)";
	private static final String IMPORT_STATEMENT_REGEXP = "([a-zA-Z_]{1}[a-zA-Z0-9_]*(\\.[a-zA-Z_]{1}[a-zA-Z0-9_]*)*)";
	private static Pattern importStatementPattern = Pattern.compile(IMPORT_STATEMENT_REGEXP);
	public static final String EMPTY = "";


	public static Set<String> onlyImports(String type) {

		Set<String> imports = new HashSet<String>();
		Matcher matcher = importStatementPattern.matcher(type);

		while (matcher.find()) {
			imports.add(matcher.group());
		}
		return imports;
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

}
