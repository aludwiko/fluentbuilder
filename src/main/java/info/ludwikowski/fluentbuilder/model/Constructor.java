/*
 * Created on 3 wrz 2014 21:35:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.model;

import static info.ludwikowski.fluentbuilder.util.NameUtils.removePackageNameFromFullyQualifiedName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;

/**
 * Respresentation of class constructor;
 * 
 * @author Andrzej Ludwikowski
 * 
 */
public final class Constructor implements Comparable<Constructor> {

	private static final String PARAM_SEPARATOR = ", ";
	private static final String PARAM_NAME_PREFIX = "arg";
	private final List<ConstructorParam> params = new ArrayList<ConstructorParam>();


	private Constructor() {}

	public static Constructor create(java.lang.reflect.Constructor<?> constructor) {

		Constructor constr = new Constructor();

		Class<?>[] parameterTypes = constructor.getParameterTypes();

		int i = 0;
		for (Class<?> paramType : parameterTypes) {
			constr.params.add(new ConstructorParam(PARAM_NAME_PREFIX + i, paramType.getName()));
			i++;
		}

		return constr;
	}

	public static Constructor create(ExecutableType executableType, Element element) {
		Constructor constr = new Constructor();
		final List<? extends VariableElement> parameterNames = ((ExecutableElement) element).getParameters();
		final List<? extends TypeMirror> parameterTypes = executableType.getParameterTypes();
		int counter = 0;
		for (VariableElement parameterName : parameterNames) {
			String name = parameterName.toString();
			String type = parameterTypes.get(counter).toString();
			constr.params.add(new ConstructorParam(name, type));
			counter++;
		}
		return constr;
	}

	private int howManyParams() {
		return params.size();
	}

	public List<String> getParamsTypes() {
		List<String> types = new ArrayList<String>();
		for (ConstructorParam param : params) {
			types.add(param.getType());
		}
		return types;
	}

	public boolean isDefault() {
		return params.size() == 0;
	}

	public String printParamsWithTypes() {

		if (isDefault()) {
			return "";
		}

		StringBuilder paramsWithTypes = new StringBuilder();

		for (ConstructorParam param : params) {
			paramsWithTypes.append(removePackageNameFromFullyQualifiedName(param.getType()) + " " + param.getName() + PARAM_SEPARATOR);
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

		for (ConstructorParam param : params) {
			paramsNames.append(param.getName() + PARAM_SEPARATOR);
		}

		return removeLastComma(paramsNames.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((params == null) ? 0 : params.hashCode());
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
		if (params == null) {
			if (other.params != null)
				return false;
		}
		else if (!params.equals(other.params))
			return false;
		return true;
	}

	@Override
	public int compareTo(Constructor o) {
		int result = howManyParams() - o.howManyParams();

		if (result == 0){
			for (int i = 0; i < params.size(); i++) {

				int paramComparison = params.get(i).compareTo(o.params.get(i));

				if (paramComparison != 0) {
					return paramComparison;
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Constructor [params=");
		builder.append(params);
		builder.append("]");
		return builder.toString();
	}

}
