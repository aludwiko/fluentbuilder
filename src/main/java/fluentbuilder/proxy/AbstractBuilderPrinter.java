/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package fluentbuilder.proxy;

import static org.apache.commons.lang.StringUtils.capitalize;

import java.io.PrintStream;
import java.util.List;
import java.util.Set;

import fluentbuilder.common.BuilderPrinter;
import fluentbuilder.common.FieldDto;


public class AbstractBuilderPrinter extends BuilderPrinter {

	public AbstractBuilderPrinter(PrintStream printStream) {
		super(printStream);
	}

	public void printComment(String className) {
		println("/** ");
		println(" * Fluent builder for " + className);
		println(" * ");
		println(" */");
	}

	public void printBuilderBegin(String className, String builderName) {
		println("public abstract class #0 extends AbstractBuilder<#1, #0>{", builderName, className);
	}

	public void printBuilderBody(List<FieldDto> fields, String builderName, String methodPrefix) {

		increaseIndentation();
		println();

		for (FieldDto field : fields) {

			String fieldName = field.getName();

			println("public abstract #0 #1#2(#3 #4);",
					builderName,
					methodPrefix,
					capitalize(fieldName),
					field.getType(),
					field.getName());
		}

		decreaseIndentation();
	}

	public void printBuilderEnd() {
		println("}");
	}

	public void printCreateMethod(String realBuilderName) {
		increaseIndentation();
		println();
		println("public static #0 create(){", realBuilderName);
		increaseIndentation();
		println("return AbstractBuilderFactory.createImplementation(#0.class);", realBuilderName);
		decreaseIndentation();
		println("}");
		decreaseIndentation();
	}

	public void printVarargsMethods(List<FieldDto> collectionFields, String realBuilderName, String methodPrefix) {
		increaseIndentation();
		println();
		
		for (FieldDto field : collectionFields) {

			String fieldName = field.getName();
			
			println("public #0 #1#2(#3... #4){", realBuilderName, methodPrefix, capitalize(fieldName), field.getType(), fieldName);
			increaseIndentation();
			printCollectionCreation(methodPrefix, fieldName, field.getCollection());
			decreaseIndentation();
			println("}");
		}
		decreaseIndentation();
	}

	private void printCollectionCreation(String methodPrefix, String fieldName, Class<?> collection) {
		
		if (collection.isAssignableFrom(List.class)) {
			println("return #0#1(Lists.newArrayList(#2));", methodPrefix, capitalize(fieldName), fieldName);
		}
		else if (collection.isAssignableFrom(Set.class)) {
			println("return #0#1(Sets.newHashSet(#2));", methodPrefix, capitalize(fieldName), fieldName);
		}
	}

}
