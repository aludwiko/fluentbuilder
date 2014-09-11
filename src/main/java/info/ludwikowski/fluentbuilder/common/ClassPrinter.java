/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.common;

import static info.ludwikowski.fluentbuilder.util.StringUtils.repeat;
import info.ludwikowski.fluentbuilder.model.ClassMirror;

import java.util.Set;


public abstract class ClassPrinter {

	private static final String END_OF_LINE = System.getProperty("line.separator");
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

	protected void print(String text) {

		printIndentation(indentationLevel);
		append(text);
	}

	private void appendln(String text) {
		stringBuffer.append(text);
		println();
	}

	private void append(String text) {
		stringBuffer.append(text);
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
		stringBuffer.append(END_OF_LINE);
	}

	protected void decreaseIndentation() {
		indentationLevel--;
	}

	protected void increaseIndentation() {
		indentationLevel++;
	}
}
