/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import info.ludwikowski.fluentbuilder.util.NameUtils;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * This class determines the necessary imports for the generated abstract
 * builder and builder classes.
 * 
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public final class ImportsFactory {

	private ImportsFactory() {

	}

	/**
	 * Creates the necessary imports for a type.
	 * 
	 * @param type the fully qualified name of the type
	 * @return a collection of fully qualified class names as Strings.
	 */
	public static Collection<String> createNecessaryImportsForTypeInClass(final String type) {

		final Set<String> imports = new HashSet<String>();
		if (TypeUtils.isListOrSet(type)) {
			addCollectionImports(type, imports);
		}
		else {
			if (!TypeUtils.isPrimitiveType(type)) {
				imports.add(type);
			}
		}

		return imports;
	}

	private static void addCollectionImports(final String type, final Set<String> imports) {

		if (TypeUtils.isList(type)) {
			imports.add("java.util.ArrayList");
			imports.add("java.util.List");
		}
		else if (TypeUtils.isSet(type)) {
			imports.add("java.util.HashSet");
			imports.add("java.util.Set");
		}
		imports.add("java.util.Arrays");
	}

	/**
	 * Checks if a given String contains import statements and returns them as a
	 * Set.
	 * 
	 * @param type String which is checked for imports
	 * @return found imports as a Set of Strings
	 */
	public static Set<String> onlyImports(final String type) {

		final Set<String> imports = new HashSet<String>();
		final Matcher matcher = NameUtils.IMPORT_STATEMENT_PATTERN.matcher(type);

		while (matcher.find()) {
			imports.add(matcher.group());
		}
		return imports;
	}

}
