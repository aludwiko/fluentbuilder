package abstractfluentbuilder;

import static org.springframework.util.StringUtils.hasText;
import fluentbuilder.FluentBuilderFieldProvider;


public class AbstractBuilderGenerator {

	private Class<?> clazz;
	private String methodPrefix = "with";
	private String builderName;
	private AbstractBuilderPrinter printer = new AbstractBuilderPrinter(System.out);
	private FluentBuilderFieldProvider fieldProvider = new FluentBuilderFieldProvider();


	private AbstractBuilderGenerator(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 
	 * @param clazz - class for builder generation
	 * @return
	 */
	public static AbstractBuilderGenerator forClass(Class<?> clazz) {
		return new AbstractBuilderGenerator(clazz);
	}

	/**
	 * default generated method name prefix is 'with', e.g. for field id method name will be 'withId(long id)';
	 * @param methodPrefix - new method prefix
	 * @return 
	 */
	public AbstractBuilderGenerator withMethodPrefix(String methodPrefix) {
		this.methodPrefix = methodPrefix;
		return this;
	}

	/**
	 * default is ClassNameBuilder
	 * @param methodPrefix - new builder name (camelcase notation)
	 * @return 
	 */
	public AbstractBuilderGenerator withBuilderName(String builderName) {
		this.builderName = builderName;
		return this;
	}

	/**
	 * print builder to {@link System#out}
	 */
	public void printBuilder() {

		String className = clazz.getSimpleName();
		String realBuilderName = builderName(className, builderName);

		printer.printComment(className);
		printer.printBuilderBegin(className, realBuilderName);
		printer.printBuilderBody(fieldProvider.findProperFields(clazz), realBuilderName, methodPrefix);
		printer.printBuilderEnd();
	}

	private String builderName(String className, String builderName) {
		if (hasText(builderName)) {
			return builderName;
		}
		return className + "Builder";
	}

}
