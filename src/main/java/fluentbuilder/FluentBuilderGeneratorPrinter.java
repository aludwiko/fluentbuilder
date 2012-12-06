/* 
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package fluentbuilder;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.apache.commons.lang.StringUtils.uncapitalize;

import java.io.PrintStream;


public class FluentBuilderGeneratorPrinter {

	private PrintStream printStream;
	private static String INDENTATION = "\t";
	private int indentationLevel;


	public FluentBuilderGeneratorPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void printComment(String className) {

		printlnWithCurrentIndentation("/** ");
		printlnWithCurrentIndentation(" * Fluent builder for " + className);
		printlnWithCurrentIndentation(" * @formatter:off");
		printlnWithCurrentIndentation(" */");
	}

	protected void printlnWithCurrentIndentation(String text) {
		printIndentation(indentationLevel);
		printStream.println(text);
	}

	private void printIndentation(int level) {

		printStream.print(repeat(INDENTATION, level));
	}

	public void printBuilderClass(String builderName) {

		printlnWithCurrentIndentation(builderStaticClass(builderName));
		increaseIndentation();
		printlnWithCurrentIndentation(builderReturnStaticClass(builderName));
		decreaseIndentation();
		printlnWithCurrentIndentation("}");
		printNewLine();
	}

	/**
	 * "return new Builder();"
	 * @param builderName
	 * @return
	 */
	private String builderReturnStaticClass(String builderName) {

		StringBuffer returnClass = new StringBuffer("return new ");

		returnClass.append(capitalize(builderName));
		returnClass.append("();");

		return returnClass.toString();
	}

	/**
	 *  "public static Builder builder() {"
	 */
	private String builderStaticClass(String builderName) {

		StringBuffer staticClass = new StringBuffer("public static ");

		staticClass.append(capitalize(builderName));
		staticClass.append(" ");
		staticClass.append(uncapitalize(builderName));
		staticClass.append("() {");

		return staticClass.toString();
	}

	private void printNewLine() {
		printStream.println();
	}

	private void decreaseIndentation() {
		indentationLevel--;
	}

	private void increaseIndentation() {
		indentationLevel++;
	}

	public void printBuilderBody(Class<?> clazz, String methodPrefix, String launchBuildMethodName, String builderName) {

		printlnWithCurrentIndentation("public static class " + capitalize(builderName) + " {");
		increaseIndentation();
		printNewLine();
//		print("private final " + className + " " + varName + " = new " + className + "();");
	}

}
