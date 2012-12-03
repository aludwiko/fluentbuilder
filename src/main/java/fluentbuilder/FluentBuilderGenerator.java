/*
 * Created on 03-12-2012 19:25:53 by Andrzej Ludwikowski
 */

package fluentbuilder;


/**
 * 
 * @author Andrzej Ludwikowski
 */
public class FluentBuilderGenerator {

	private Class<?> clazz;
	private String methodPrefix = "with";
	private String builderName = "builder";


//	private static FluentBuilderGeneratorPrinter printer = new FluentBuilderGeneratorPrinter();


	private FluentBuilderGenerator(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 
	 * @param clazz - class for builder generation
	 * @return
	 */
	public static FluentBuilderGenerator forClass(Class<?> clazz) {
		return new FluentBuilderGenerator(clazz);
	}

	/**
	 * default generated method name prefix is 'with', e.g. for field id method name will be 'withId(long id)';
	 * @param methodPrefix - new method prefix
	 */
	public void withMethodPrefix(String methodPrefix) {
		this.methodPrefix = methodPrefix;
	}

	/**
	 * default is 'builder'
	 * @param methodPrefix - new builder name
	 */
	public void withBuilderName(String builderName) {
		this.builderName = builderName;
	}

	public void printBuilder() {

		//
	}
}
