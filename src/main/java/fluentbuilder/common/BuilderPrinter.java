/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package fluentbuilder.common;

import static org.apache.commons.lang.StringUtils.repeat;

import java.io.PrintStream;


public abstract class BuilderPrinter {

	private static String INDENTATION = "\t";
	private int indentationLevel = 0;
	private PrintStream printStream;


	public BuilderPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}

	protected void println(String text, String... values) {

		printIndentation(indentationLevel);

		int i = 0;
		for (String value : values) {
			text = text.replaceAll("#" + i, value);
			i++;
		}
		printStream.println(text);
	}

	protected void printIndentation(int level) {

		printStream.print(repeat(INDENTATION, level));
	}

	protected void println() {
		printStream.println();
	}

	protected void decreaseIndentation() {
		indentationLevel--;
	}

	protected void increaseIndentation() {
		indentationLevel++;
	}
}
