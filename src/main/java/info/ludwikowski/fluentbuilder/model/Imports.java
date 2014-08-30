/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class manages imports as Strings in a Set.
 * 
 * @author Andrzej Ludwikowski
 */
public class Imports {

	private static final String JAVA_LANG = "java.lang";
	private final Set<String> imports = new TreeSet<String>();


	/**
	 * Adds all imports of an Imports object to the managed imports.
	 * 
	 * @param imports Imports object which is added to the managed imports.
	 */
	public final void addAll(final Imports imports) {

		if (imports != null) {
			addAll(imports.asSet());
		}
	}

	/**
	 * Adds imports from a Set to the managed imports. If import starts with "java.lang" then it is ommited.
	 * 
	 * @param imports a Set of imports which is added to the managed imports.
	 */
	public final void addAll(final Set<String> imports) {

		for (final String importString : imports) {

			if (!importString.contains(JAVA_LANG)) {
				this.imports.add(importString);
			}
		}
	}

	/**
	 * Returns all managed imports as a Set.
	 * 
	 * @return managed imports as a Set
	 */
	public final Set<String> asSet() {
		return imports;
	}

}
