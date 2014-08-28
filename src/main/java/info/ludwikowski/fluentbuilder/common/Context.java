/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import org.apache.commons.lang.StringUtils;

/**
 * This class provides default properties for the FluentBuilderGenerator and the
 * ability to change them. It is used by the generator and by the builder.
 * 
 * @author Andrzej Ludwikowski
 */
// CHECKSTYLE IGNORE MethodCountCheck FOR NEXT 1 LINES
public class Context {

	private String setterMethodPrefix = "with";
	private String constructorMethodPrefix = "constructedWith";
	private String builderClassPostfix = "Builder";
	private String abstractBuilderClassPrefix = "Abstract";
	private String staticCreateMethodName;
	private String buildMethodName;
	private String ignoredClassPrefix;
	private boolean staticCreate = true;
	private boolean varArgsForCollections = true;
	private boolean useIndefiniteArticles = true;


	/**
	 * Returns the prefix of setter methods.
	 * 
	 * @return setter method prefix
	 */
	public final String getMethodPrefix() {
		return setterMethodPrefix;
	}

	/**
	 * Sets the prefixes for setter methods.
	 * 
	 * @param methodPrefix setter method prefix
	 */
	public final void setMethodPrefix(final String methodPrefix) {
		if (StringUtils.isNotBlank(methodPrefix)) {
			this.setterMethodPrefix = methodPrefix;
		}
	}

	/**
	 * Returns the prefix for constructor selection methods.
	 * 
	 * @return constructor selection method prefix
	 */
	public final String getConstructorMethodPrefix() {
		return constructorMethodPrefix;
	}

	/**
	 * Sets the constructor selection method prefix.
	 * 
	 * @param constructorMethodPrefix new constructor selection method prefix
	 */
	public final void setConstructorMethodPrefix(final String constructorMethodPrefix) {
		if (StringUtils.isNotBlank(constructorMethodPrefix)) {
			this.constructorMethodPrefix = constructorMethodPrefix;
		}
	}

	/**
	 * Returns the postfix for builder classes.
	 * 
	 * @return builder class postfix
	 */
	public final String getBuilderClassPostfix() {
		return builderClassPostfix;
	}

	/**
	 * Set the postfix for builder classes.
	 * 
	 * @param builderClassPostfix new builder class postfix
	 */
	public final void setBuilderClassPostfix(final String builderClassPostfix) {
		if (StringUtils.isNotBlank(builderClassPostfix)) {
			this.builderClassPostfix = builderClassPostfix;
		}
	}

	/**
	 * Returns the prefix for {@link AbstractBuilder} classes.
	 * 
	 * @return AbstractBuilder prefix
	 */
	public final String getAbstractBuilderClassPrefix() {
		return abstractBuilderClassPrefix;
	}

	/**
	 * Set the prefix for {@link AbstractBuilder} classes.
	 * 
	 * @param abstractBuilderClassPrefix new AbstractBuilder prefix
	 */
	public final void setAbstractBuilderClassPrefix(final String abstractBuilderClassPrefix) {
		if (StringUtils.isNotBlank(abstractBuilderClassPrefix)) {
			this.abstractBuilderClassPrefix = abstractBuilderClassPrefix;
		}
	}

	/**
	 * Returns the name of the static create method.
	 * 
	 * @return name of the static create method
	 */
	public final String getStaticCreateMethodName() {
		return staticCreateMethodName;
	}

	/**
	 * Set the name of the static create method.
	 * 
	 * @param staticCreateMethodName new name of the static create method
	 */
	public final void setStaticCreateMethodName(final String staticCreateMethodName) {
		if (StringUtils.isNotBlank(staticCreateMethodName)) {
			this.staticCreateMethodName = staticCreateMethodName;
		}
	}

	/**
	 * Checks if a static create method should be generated.
	 * 
	 * @return True if a static create method should be printed, false if not.
	 */
	public final boolean isStaticCreate() {
		return staticCreate;
	}

	/**
	 * Selects if a static create method should be generated or not.
	 * 
	 * @param staticCreate True if a static create method should be printed,
	 *            false if not.
	 */
	public final void setStaticCreate(final boolean staticCreate) {
		this.staticCreate = staticCreate;
	}

	/**
	 * Checks if setter methods with variable arguments should be printed for
	 * collection fields.
	 * 
	 * @return True if VarArgs setter methods should be printed, false if not.
	 */
	public final boolean isVarArgsForCollections() {
		return varArgsForCollections;
	}

	/**
	 * Selects if setter methods with variable arguments should be printed for
	 * collection fields.
	 * 
	 * @param varArgsForCollections True if VarArgs setter methods should be
	 *            printed, false if not.
	 */
	public final void setVarArgsForCollections(final boolean varArgsForCollections) {
		this.varArgsForCollections = varArgsForCollections;
	}

	/**
	 * Returns the method name of the build method.
	 * 
	 * @return name of the build method
	 */
	public final String getBuildMethodName() {
		return buildMethodName;
	}

	/**
	 * Sets the method name of the build method.
	 * 
	 * @param buildMethodName new name of the build method
	 */
	public final void setBuildMethodName(final String buildMethodName) {
		if (StringUtils.isNotBlank(buildMethodName)) {
			this.buildMethodName = buildMethodName;
		}
	}

	/**
	 * Returns the prefix which is ignored when getting a class name.
	 * 
	 * @return the ignored prefix
	 */
	public final String getIgnoredClassPrefix() {
		return ignoredClassPrefix;
	}

	/**
	 * Sets the prefix which is ignored when getting a class name.
	 * 
	 * @param ignoredClassPostfix the newly ignored prefix.
	 */
	public final void setIgnoredClassPrefix(final String ignoredClassPostfix) {
		this.ignoredClassPrefix = ignoredClassPostfix;
	}

	/**
	 * Checks if an indefinite article should be printed in front of the static
	 * builder create method.
	 * 
	 * @return True if an indefinite article should be printed, false if not.
	 */
	public final boolean isUseIndefiniteArticles() {
		return useIndefiniteArticles;
	}

	/**
	 * Selects if an indefinite article should be printed in front of the static
	 * builder create method.
	 * 
	 * @param useIndefiniteArticles True if an indefinite article should be
	 *            printed, false if not.
	 */
	public final void setUseIndefiniteArticles(final boolean useIndefiniteArticles) {
		this.useIndefiniteArticles = useIndefiniteArticles;
	}

	// DesignForExtension is ignored for toString() because the base class and
	// sub classes have to override it.
	// CHECKSTYLE IGNORE NCSS|DesignForExtension FOR NEXT 2 LINES
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Context [methodPrefix=");
		builder.append(setterMethodPrefix);
		builder.append(", builderClassPostfix=");
		builder.append(builderClassPostfix);
		builder.append(", abstractBuilderClassPrefix=");
		builder.append(abstractBuilderClassPrefix);
		builder.append(", staticCreateMethodName=");
		builder.append(staticCreateMethodName);
		builder.append(", buildMethodName=");
		builder.append(buildMethodName);
		builder.append(", ignoredClassPrefix=");
		builder.append(ignoredClassPrefix);
		builder.append(", staticCreate=");
		builder.append(staticCreate);
		builder.append(", varargsForCollections=");
		builder.append(varArgsForCollections);
		builder.append(", useIndefineArticles=");
		builder.append(useIndefiniteArticles);
		builder.append("]");
		return builder.toString();
	}

}
