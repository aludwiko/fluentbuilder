package info.ludwikowski.common;

import static info.ludwikowski.util.StringUtils.hasText;

public class Context {

	private String methodPrefix = "with";
	private String builderClassPostfix = "Builder";
	private String abstractBuilderClassPrefix = "Abstract";
	private String staticCreateMethodName;
	private String buildMethodName;
	private String ignoredClassPrefix;
	private boolean staticCreate = true;
	private boolean varargsForCollections = true;
	private boolean useIndefiniteArticles = true;


	public String getMethodPrefix() {
		return methodPrefix;
	}

	public void setMethodPrefix(String methodPrefix) {
		if (hasText(methodPrefix)) {
			this.methodPrefix = methodPrefix;
		}
	}

	public String getBuilderClassPostfix() {
		return builderClassPostfix;
	}

	public void setBuilderClassPostfix(String builderClassPostfix) {
		if (hasText(builderClassPostfix)) {
			this.builderClassPostfix = builderClassPostfix;
		}
	}

	public String getAbstractBuilderClassPrefix() {
		return abstractBuilderClassPrefix;
	}

	public void setAbstractBuilderClassPrefix(String abstractBuilderClassPrefix) {
		if (hasText(abstractBuilderClassPrefix)) {
			this.abstractBuilderClassPrefix = abstractBuilderClassPrefix;
		}
	}

	public String getStaticCreateMethodName() {
		return staticCreateMethodName;
	}

	public void setStaticCreateMethodName(String staticCreateMethodName) {
		if (hasText(staticCreateMethodName)) {
			this.staticCreateMethodName = staticCreateMethodName;
		}
	}

	public boolean isStaticCreate() {
		return staticCreate;
	}

	public void setStaticCreate(boolean staticCreate) {
		this.staticCreate = staticCreate;
	}

	public boolean isVarargsForCollections() {
		return varargsForCollections;
	}

	public void setVarargsForCollections(boolean varargsForCollections) {
		this.varargsForCollections = varargsForCollections;
	}

	public String getBuildMethodName() {
		return buildMethodName;
	}

	public void setBuildMethodName(String buildMethodName) {
		if (hasText(buildMethodName)) {
			this.buildMethodName = buildMethodName;
		}
	}

	public String getIgnoredClassPrefix() {
		return ignoredClassPrefix;
	}

	public void setIgnoredClassPrefix(String ignoredClassPostfix) {
		this.ignoredClassPrefix = ignoredClassPostfix;
	}

	public boolean isUseIndefiniteArticles() {
		return useIndefiniteArticles;
	}

	public void setUseIndefiniteArticles(boolean useIndefiniteArticles) {
		this.useIndefiniteArticles = useIndefiniteArticles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Context [methodPrefix=");
		builder.append(methodPrefix);
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
		builder.append(varargsForCollections);
		builder.append(", useIndefineArticles=");
		builder.append(useIndefiniteArticles);
		builder.append("]");
		return builder.toString();
	}
}
