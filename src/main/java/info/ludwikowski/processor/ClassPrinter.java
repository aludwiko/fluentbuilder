/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static info.ludwikowski.util.StringUtils.repeat;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;


public abstract class ClassPrinter {

	private static String INDENTATION = "\t";
	private int indentationLevel = 0;
	private PrintWriter printWriter;


	public ClassPrinter(Writer writer) {
		this.printWriter = new PrintWriter(writer);
	}

	public abstract void printClassWithBody();

	public abstract Set<String> getFullClassNamesForImports();

	public abstract String getPackageName();

	public abstract void printClassComment();

	public void printClass() {

		println("package #0;", getPackageName());
		println();
		printImportStatements();
		println();
		println();
		printClassComment();
		printClassWithBody();
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
		printWriter.println(text);
	}

	protected void println(String text, String... values) {

		printIndentation(indentationLevel);

		int i = 0;
		for (String value : values) {
			text = text.replaceAll("#" + i, value);
			i++;
		}
		printWriter.println(text);
	}

	protected void printIndentation(int level) {

		printWriter.print(repeat(INDENTATION, level));
	}

	protected void println() {
		printWriter.println();
	}

	protected void decreaseIndentation() {
		indentationLevel--;
	}

	protected void increaseIndentation() {
		indentationLevel++;
	}
}
