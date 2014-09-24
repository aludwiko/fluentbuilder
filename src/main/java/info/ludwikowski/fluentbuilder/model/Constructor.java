/*
 * Created on 3 wrz 2014 21:35:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.util.NameUtils.removePackageNameFromFullyQualifiedName;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Respresentation of class constructor;
 * 
 * @author Andrzej Ludwikowski
 * 
 */
public final class Constructor implements Comparable<Constructor> {

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

	private int howManyParams() {
		return paramsTypes.size();
	}

	public List<String> getParamsTypes() {
		return unmodifiableList(paramsTypes);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paramsTypes == null) ? 0 : paramsTypes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constructor other = (Constructor) obj;
		if (paramsTypes == null) {
			if (other.paramsTypes != null)
				return false;
		} else if (!paramsTypes.equals(other.paramsTypes))
			return false;
		return true;
	}

	@Override
	public int compareTo(Constructor o) {
		int result = howManyParams() - o.howManyParams();

		if (result == 0){
			for (int i = 0; i < paramsTypes.size(); i++) {

				int paramComparison = paramsTypes.get(i).compareTo(o.paramsTypes.get(i));

				if (paramComparison != 0) {
					return paramComparison;
				}
			}
		}
		return result;
	}
}
