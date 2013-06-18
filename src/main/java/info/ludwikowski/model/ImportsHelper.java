package info.ludwikowski.model;

import static info.ludwikowski.util.TypeUtils.isList;
import static info.ludwikowski.util.TypeUtils.isListOrSet;
import info.ludwikowski.util.StringUtils;
import info.ludwikowski.util.TypeUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;


public class ImportsHelper {

	public static Collection<String> createNecessaryImports(String type) {

		Set<String> imports = new HashSet<String>();

		if (isListOrSet(type)) {
			if (isList(type)) {
				imports.add("java.util.ArrayList");
				imports.add("java.util.List");
			}
			else if (TypeUtils.isSet(type)) {
				imports.add("java.util.HashSet");
				imports.add("java.util.Set");
			}
			imports.add("java.util.Arrays");
		}
		else {
			imports.add(type);
		}

		return imports;
	}

	public static Set<String> onlyImports(String type) {
	
		Set<String> imports = new HashSet<String>();
		Matcher matcher = StringUtils.importStatementPattern.matcher(type);
	
		while (matcher.find()) {
			imports.add(matcher.group());
		}
		return imports;
	}

}
