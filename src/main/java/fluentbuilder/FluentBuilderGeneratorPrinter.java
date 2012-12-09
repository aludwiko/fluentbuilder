/* 
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package fluentbuilder;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.apache.commons.lang.StringUtils.uncapitalize;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;


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

	private void printlnWithCurrentIndentation(String text) {
		printIndentation(indentationLevel);
		printStream.println(text);
	}
	
	private void println(String text, String... values) {

		printIndentation(indentationLevel);

		int i = 0;
		for (String value : values) {
			text = text.replaceAll("#" + i, value);
			i++;
		}
		printStream.println(text);
	}

	private void printIndentation(int level) {

		printStream.print(repeat(INDENTATION, level));
	}

	public void printBuilderStaticInvocation(String builderName) {

		println("public static #0 #1() {", capitalize(builderName), uncapitalize(builderName));
		increaseIndentation();
		println("return new #0();", builderName);
		decreaseIndentation();
		printlnWithCurrentIndentation("}");
		println();
	}

	private void println() {
		printStream.println();
	}

	private void decreaseIndentation() {
		indentationLevel--;
	}

	private void increaseIndentation() {
		indentationLevel++;
	}

	public void printBuilderBegin(String className, String builderName) {

		println("public static class #0 {", capitalize(builderName));
		increaseIndentation();
		println();
		println("private final #0 #1 = new #0();", capitalize(className), uncapitalize(className));
		println();
	}

	/**
	 * for each field prints new "with" statement, eg. for <code>name</code> will be
	 * <br/> 
	 * <code>public Builder withName(String name) { sample.name = name; return this; }</code>
	 * 
	 * @param fields
	 * @param builderName
	 * @param className
	 * @param methodPrefix
	 */
	public void printBuilderBody(List<Field> fields, String builderName, String className, String methodPrefix) {

		for (Field field : fields) {

			String fieldName = field.getName();

			println("public #0 #1#2(#3 #4) { #5.#4 = #4; return this; }",
					capitalize(builderName),
					methodPrefix,
					capitalize(fieldName),
					field.getType().getSimpleName(),
					fieldName,
					uncapitalize(className));
		}
	}

	public void printBuilderEnd(String className, String launchBuildMethodName) {

		println();
		println("public #0 #1() { return #2;}", className, launchBuildMethodName, uncapitalize(className));
		decreaseIndentation();
		printlnWithCurrentIndentation("}");
		printlnWithCurrentIndentation("/** @formatter:on */");
	}

}
