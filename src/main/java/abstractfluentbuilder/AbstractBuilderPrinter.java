package abstractfluentbuilder;

import static org.apache.commons.lang.StringUtils.capitalize;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;


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

	public void printBuilderBody(List<Field> fields, String builderName, String methodPrefix) {

		increaseIndentation();
		println();

		for (Field field : fields) {

			String fieldName = field.getName();

			// public abstract CKlasaTestowaBuilder withTypeByte(byte typeByte);
			println("public abstract #0 #1#2(#3 #4);",
					builderName,
					methodPrefix,
					capitalize(fieldName),
					field.getType().toString(),
					fieldName);
		}

		decreaseIndentation();
	}

	public void printBuilderEnd() {
		println("}");
	}

}
