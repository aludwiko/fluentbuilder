/* 
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package fluentbuilder;

import static org.apache.commons.lang.StringUtils.repeat;

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

}
