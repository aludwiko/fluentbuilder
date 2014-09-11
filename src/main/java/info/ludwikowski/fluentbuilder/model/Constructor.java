/*
 * Created on 3 wrz 2014 21:35:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.util.NameUtils.removePackageNameFromFullyQualifiedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Respresentation of class constructor;
 * 
 * @author Andrzej Ludwikowski
 * 
 */
public class Constructor {

	private static final String PARAM_SEPARATOR = ", ";
	private static final String PARAM_NAME_PREFIX = "arg";
	private final List<String> paramsTypes = new ArrayList<String>();


	private Constructor() {}

	public static Constructor create(java.lang.reflect.Constructor<?> constructor) {

		Constructor constr = new Constructor();

		Class<?>[] parameterTypes = constructor.getParameterTypes();

		for (Class<?> paramType : parameterTypes) {
			constr.paramsTypes.add(paramType.getName());
		}

		return constr;
	}

	public List<String> getParamsTypes() {
		return paramsTypes;
	}

	public boolean isDefault() {
		return paramsTypes.size() == 0;
	}

	public String printParamsWithTypes() {

		if (isDefault()) {
			return "";
		}

		StringBuilder paramsWithTypes = new StringBuilder();

		for (int i = 0; i < paramsTypes.size(); i++) {
			paramsWithTypes.append(removePackageNameFromFullyQualifiedName(paramsTypes.get(i)) + " " + PARAM_NAME_PREFIX + i + PARAM_SEPARATOR);
		}

		return removeLastComma(paramsWithTypes.toString());
	}

	private String removeLastComma(String paramsWithTypes) {
		return paramsWithTypes.substring(0, paramsWithTypes.length() - PARAM_SEPARATOR.length());
	}

	public String printParamsNames() {
		if (isDefault()) {
			return "";
		}

		StringBuilder paramsNames = new StringBuilder();

		for (int i = 0; i < paramsTypes.size(); i++) {
			paramsNames.append(PARAM_NAME_PREFIX + i + PARAM_SEPARATOR);
		}

		return removeLastComma(paramsNames.toString());
	}
}
