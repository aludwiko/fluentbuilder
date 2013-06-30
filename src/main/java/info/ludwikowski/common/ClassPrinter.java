/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.common;

import static info.ludwikowski.util.StringUtils.EMPTY;
import static info.ludwikowski.util.StringUtils.addIndefineArticle;
import static info.ludwikowski.util.StringUtils.hasText;
import static info.ludwikowski.util.StringUtils.repeat;
import static info.ludwikowski.util.StringUtils.uncapitalize;
import info.ludwikowski.model.ClassMirror;

import java.util.Set;


public abstract class ClassPrinter {

	private static String INDENTATION = "\t";
	private int indentationLevel = 0;
	private StringBuffer stringBuffer = new StringBuffer();

	protected final Context context;
	protected final ClassMirror classMirror;


	public ClassPrinter(ClassMirror classMirror, Context context) {
		this.classMirror = classMirror;
		this.context = context;
	}

	public abstract String getPackageName();
	
	public abstract String builderName();

	protected abstract void printClassWithBody();

	protected abstract Set<String> getFullClassNamesForImports();

	protected abstract void printClassComment();

	/**
	 * class name with package
	 */
	public abstract String getFullClassName();
	
	public String printClass() {

		println("package #0;", getPackageName());
		println();
		printImportStatements();
		println();
		println();
		printClassComment();
		printClassWithBody();

		return stringBuffer.toString();
	}

	private void printImportStatements() {

		for (String fullClassName : getFullClassNamesForImports()) {
			printImportStatement(fullClassName);
		}
	}

	private void printImportStatement(String classFullName) {
		println("import #0;", classFullName);
	}

	protected void println(String text) {

		printIndentation(indentationLevel);
		appendln(text);
	}

	private void appendln(String text) {
		stringBuffer.append(text);
		stringBuffer.append("\n");
	}

	protected void println(String text, String... values) {

		printIndentation(indentationLevel);

		int i = 0;
		for (String value : values) {
			text = text.replaceAll("#" + i, value);
			i++;
		}
		appendln(text);
	}

	protected void printIndentation(int level) {

		stringBuffer.append(repeat(INDENTATION, level));
	}

	protected void println() {
		stringBuffer.append("\n");
	}

	protected void decreaseIndentation() {
		indentationLevel--;
	}

	protected void increaseIndentation() {
		indentationLevel++;
	}
	
	protected void printCreateMethod() {

		if (!context.isStaticCreate()) {
			return;
		}

		increaseIndentation();
		println();
		println("public static #0 #1(){", builderName(), createBuilderMethodName());
		increaseIndentation();
		println("return AbstractBuilderFactory.createImplementation(#0.class);", builderName());
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

		if (context.isUseIndefineArticles()) {
			className = addIndefineArticle(className);
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
