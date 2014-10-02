/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */

package info.ludwikowski.fluentbuilder.common;

import static info.ludwikowski.fluentbuilder.util.StringUtils.EMPTY;
import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.util.NameUtils;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * This class prints a generated {@link AbstractBuilder} class for a given {@link ClassMirror}. It uses properties which
 * are defined in a {@link Context} object.
 * 
 * @author Andrzej Ludwikowski
 */
public abstract class AbstractClassPrinter {

	private static final String INDENTATION = "\t";

	private final Context context;
	private final ClassMirror classMirror;

	private int indentationLevel = 0;
	private final StringBuffer abstractBuilderBuffer = new StringBuffer();


	/**
	 * The default constructor.
	 * 
	 * @param classMirror representation of the class for which the {@link AbstractBuilder} will be printed.
	 * @param context property defining object for the printer.
	 */
	public AbstractClassPrinter(final ClassMirror classMirror, final Context context) {
		this.classMirror = classMirror;
		this.context = context;
	}

	/**
	 * Returns the full name of the ClassMirror's package.
	 * 
	 * @return full package name of the referenced {@link ClassMirror}.
	 */
	public abstract String getPackageName();

	/**
	 * Returns the name which will be used for the generated class.
	 * 
	 * @return Name of the newly printed class.
	 */
	public abstract String builderName();

	protected abstract void printClassWithBody();

	protected abstract Set<String> getFullClassNamesForImports();

	protected abstract void printClassComment();

	/**
	 * Returns the full name of the class which is represented by the referenced {@link ClassMirror} .
	 * 
	 * @return full name of the referenced class.
	 */
	public abstract String getFullClassName();

	/**
	 * Returns the generated class as a String.
	 * 
	 * @return the generated class as a String.
	 */
	public final String printClass() {

		printLine("package #0;", getPackageName());
		printLine();
		printImportStatements();
		printLine();
		printLine();
		printClassComment();
		printClassWithBody();

		return abstractBuilderBuffer.toString();
	}

	private void printImportStatements() {
		for (final String fullClassName : getFullClassNamesForImports()) {
			printImportStatement(fullClassName);
		}
	}

	private void printImportStatement(final String classFullName) {
		printLine("import #0;", classFullName);
	}

	protected final void printLine(final String text) {

		printIndentation(indentationLevel);
		appendln(text);
	}

	private void appendln(final String text) {
		abstractBuilderBuffer.append(text);
		abstractBuilderBuffer.append("\n");
	}

	protected final void printLine(final String text, final String... values) {

		printIndentation(indentationLevel);
		String replacedText = text;
		int i = 0;
		for (final String value : values) {
			replacedText = replacedText.replaceAll("#" + i, value);
			i++;
		}
		appendln(replacedText);
	}

	protected final void printIndentation(final int level) {
		abstractBuilderBuffer.append(StringUtils.repeat(INDENTATION, level));
	}

	protected final void printLine() {
		abstractBuilderBuffer.append("\n");
	}

	protected final void decreaseIndentation() {
		indentationLevel--;
	}

	protected final void increaseIndentation() {
		indentationLevel++;
	}

	protected final void printCreateMethod() {

		if (!context.isStaticCreate()) {
			return;
		}

		increaseIndentation();
		printLine();
		printLine("public static #0 #1(){", builderName(), createBuilderMethodName());
		increaseIndentation();
		printLine("return AbstractBuilderFactory.createImplementation(#0.class);", builderName());
		decreaseIndentation();
		printLine("}");
		decreaseIndentation();
	}

	private String createBuilderMethodName() {

		if (StringUtils.isNotBlank(context.getStaticCreateMethodName())) {
			return context.getStaticCreateMethodName();
		}

		String className = classMirror.getSimpleName();

		if (StringUtils.isNotBlank(context.getIgnoredClassPrefix())) {
			className = removeClassPrefix(className);
		}

		if (context.isUseIndefiniteArticles()) {
			className = NameUtils.addIndefiniteArticleInFront(className);
		}

		return StringUtils.uncapitalize(className);
	}

	private String removeClassPrefix(final String className) {

		final String ignoredClassPrefix = context.getIgnoredClassPrefix();

		if (className.startsWith(ignoredClassPrefix)) {
			return className.replaceFirst(ignoredClassPrefix, EMPTY);
		}

		return className;
	}

	/**
	 * Returns the class name of the processed class.
	 * 
	 * @return short class name
	 */
	public final String getClassName() {
		return classMirror.getSimpleName();
	}

	/**
	 * Returns the abstractBuilderBuffer for tests.
	 * 
	 * @return the content of the printed class
	 */
	public final String getBufferAsString() {
		return abstractBuilderBuffer.toString();
	}

	/**
	 * Returns the current context object.
	 * 
	 * @return the context object
	 */
	public final Context getContext() {
		return context;
	}

	/**
	 * Returns the current ClassMirror.
	 * 
	 * @return the ClassMirror object
	 */
	public final ClassMirror getClassMirror() {
		return classMirror;
	}
}
