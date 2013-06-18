package info.ludwikowski.model;

import java.util.Set;
import java.util.TreeSet;

public class Imports {

	private static final String JAVA_LANG = "java.lang";
	private Set<String> imports = new TreeSet<String>();


	public void addAll(Imports imports) {

		if (imports != null) {
			addAll(imports.asSet());
		}
	}

	public void addAll(Set<String> imports) {

		for (String importString : imports) {
			
			if (!importString.contains(JAVA_LANG)) {
				this.imports.add(importString);
			}
		}
	}

	public Set<String> asSet() {
		return imports;
	}

}
