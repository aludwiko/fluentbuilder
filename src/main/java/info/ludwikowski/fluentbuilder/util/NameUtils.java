/*
 * Created on 19-03-2013 17:03:58 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This utility class is used to perform operations on Strings.
 * 
 * @author Andrzej Ludwikowski
 */
public final class NameUtils {


	private static final String INDEFINITE_ARTICLE_BEFORE_CONSONANT = "a";
	private static final String INDEFINITE_ARTICLE_BEFORE_VOWEL = "an";
	private static final String TYPE_PART_REGEXP = "(\\.[a-zA-Z0-9]*)$";
	private static final String PACKAGE_PART_REGEXP = "([a-zA-Z0-9]*\\.)";
	private static final String IMPORT_STATEMENT_REGEXP = "([a-zA-Z_]{1}[a-zA-Z0-9_]*(\\.[a-zA-Z_]{1}[a-zA-Z0-9_]*)*)";
	public static final String EMPTY = "";
	public static final List<Character> VOWELS = new ArrayList<Character>();
	public static final Pattern IMPORT_STATEMENT_PATTERN = Pattern.compile(IMPORT_STATEMENT_REGEXP);

	static {
		VOWELS.add('a');
		VOWELS.add('e');
		VOWELS.add('i');
		VOWELS.add('o');
		VOWELS.add('u');
	}


	private NameUtils() {}

	/**
	 * Removes the package name of a fully qualified name.
	 * 
	 * @param value a fully qualified name
	 * @return simple name of the given String
	 */
	public static String removePackageNameFromFullyQualifiedName(final String value) {
		return value.replaceAll(PACKAGE_PART_REGEXP, "");
	}

	/**
	 * Returns the package name of a fully qualified name.
	 * 
	 * @param fullyQualifiedName a fully qualified name
	 * @return package name of the given String
	 */
	public static String getPackageNameFromFullyQualifiedName(final String fullyQualifiedName) {
		return fullyQualifiedName.replaceAll(TYPE_PART_REGEXP, "");
	}

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
	 * Adds an indefinite article in front of a String.
	 * 
	 * @param string String to add article to
	 * @return the String with an indefinite article in front.
	 */
	public static String addIndefiniteArticleInFront(final String string) {

		final char firstChar = Character.toLowerCase(string.charAt(0));

		if (VOWELS.contains(firstChar)) {
			return INDEFINITE_ARTICLE_BEFORE_VOWEL + string;
		}
		else {
			return INDEFINITE_ARTICLE_BEFORE_CONSONANT + string;
		}
	}

	/**
	 * Extracts the generic type as a String from a String which includes a
	 * generic declaration.
	 * 
	 * @param genericString String with a generic declaration
	 * @return generic type as a String
	 */
	public static String getOnlyGenericTypeFromGenericDeclaration(final String genericString) {
		final String newString = genericString.replaceAll(".+<", "");
		final String generic = newString.replaceAll(">", "");
		return generic;
	}
}
