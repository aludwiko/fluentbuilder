/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static info.ludwikowski.util.StringUtils.repeat;

import java.util.Set;


public abstract class ClassPrinter {

	private static String INDENTATION = "\t";
	private int indentationLevel = 0;
	private StringBuffer stringBuffer = new StringBuffer();
//	private PrintWriter printWriter = new PrintWriter(new StringWriter());


	protected abstract void printClassWithBody();

	protected abstract Set<String> getFullClassNamesForImports();

	protected abstract String getPackageName();

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
}
