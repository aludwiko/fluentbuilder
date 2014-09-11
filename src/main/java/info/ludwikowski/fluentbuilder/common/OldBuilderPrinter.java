/*
 * Created on 10-03-2013 11:17:24 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.common;

import static info.ludwikowski.fluentbuilder.util.NameUtils.addIndefiniteArticleInFront;
import static info.ludwikowski.fluentbuilder.util.StringUtils.EMPTY;
import static info.ludwikowski.fluentbuilder.util.StringUtils.hasText;
import static info.ludwikowski.fluentbuilder.util.StringUtils.uncapitalize;
import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.Constructor;

import java.util.Set;
import java.util.TreeSet;



public class OldBuilderPrinter extends ClassPrinter {


	public OldBuilderPrinter(ClassMirror classMirror, Context context) {
		super(classMirror, context);
	}

	@Override
	public String getFullClassName() {
		return getPackageName() + "." + classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

	@Override
	protected void printClassWithBody() {

		println("public abstract class #0 extends #1#0<#0> {",
				builderName(),
				context.getAbstractBuilderClassPrefix());

		printCreateMethods();
		println("}");
	}

	@Override
	protected Set<String> getFullClassNamesForImports() {
		Set<String> fullClassNames = new TreeSet<String>();
		if (context.isStaticCreate()) {
			fullClassNames.add(AbstractBuilderFactory.class.getCanonicalName());
		}
		fullClassNames.addAll(classMirror.getOnlyConstructorsImports());
		return fullClassNames;
	}

	@Override
	public String getPackageName() {
		return classMirror.getPackageName();
	}

	@Override
	protected void printClassComment() {
		println("/**");
		println(" * Fluent builder for #0.", classMirror.getSimpleName());
		println(" * Don't hesitate to put your custom methods here.");
		println(" */");
	}

	@Override
	public String builderName() {
		return classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

	protected void printCreateMethods() {

		if (!context.isStaticCreate()) {
			return;
		}

		for (Constructor constructor : classMirror.getConstructors()) {
			printCreateMethod(constructor);
		}
	}

	private void printCreateMethod(Constructor constructor) {
		increaseIndentation();
		println();
		println("public static #0 #1(#2){", builderName(), createBuilderMethodName(), constructor.printParamsWithTypes());
		increaseIndentation();
		if (constructor.isDefault()) {
			println("return AbstractBuilderFactory.createImplementation(#0.class);", builderName());
		}
		else {
			println("return AbstractBuilderFactory.createImplementation(#0.class, #1);", builderName(), constructor.printParamsNames());
		}
		decreaseIndentation();
		println("}");
		decreaseIndentation();
	}

	private String createBuilderMethodName() {

		if (hasText(context.getStaticCreateMethodName())) {
			return context.getStaticCreateMethodName();
		}

		String className = classMirror.getSimpleName();

		if (hasText(context.getIgnoredClassPrefix())) {
			className = removeClassPrefix(className);
		}

		if (context.isUseIndefiniteArticles()) {
			className = addIndefiniteArticleInFront(className);
		}

		return uncapitalize(className);
	}

	private String removeClassPrefix(String className) {

		String ignoredClassPrefix = context.getIgnoredClassPrefix();

		if (className.startsWith(ignoredClassPrefix)) {
			return className.replaceFirst(ignoredClassPrefix, EMPTY);
		}

		return className;
	}

}
