/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.generator.simple;

import static info.ludwikowski.util.StringUtils.capitalize;
import static info.ludwikowski.util.StringUtils.uncapitalize;
import info.ludwikowski.common.BuilderPrinter;
import info.ludwikowski.common.FieldDto;

import java.io.PrintStream;
import java.util.List;


public class SimpleBuilderGeneratorPrinter extends BuilderPrinter {

	public SimpleBuilderGeneratorPrinter(PrintStream printStream) {
		super(printStream);
	}

	public void printComment(String className) {

		println("/** ");
		println(" * Fluent builder for " + className);
		println(" * @formatter:off");
		println(" */");
	}

	public void printBuilderStaticInvocation(String builderName) {

		println("public static #0 #1() {", capitalize(builderName), uncapitalize(builderName));
		increaseIndentation();
		println("return new #0();", capitalize(builderName));
		decreaseIndentation();
		println("}");
		println();
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
	public void printBuilderBody(List<FieldDto> fields, String builderName, String className, String methodPrefix) {

		for (FieldDto field : fields) {

			String fieldName = field.getName();

			println("public #0 #1#2(#3 #4) { #5.#4 = #4; return this; }",
					capitalize(builderName),
					methodPrefix,
					capitalize(fieldName),
					field.getType(),
					field.getName(),
					uncapitalize(className));
		}
	}

	public void printBuilderEnd(String className, String launchBuildMethodName) {

		println();
		println("public #0 #1() { return #2;}", className, launchBuildMethodName, uncapitalize(className));
		decreaseIndentation();
		println("}");
		println("/** @formatter:on */");
	}

}
