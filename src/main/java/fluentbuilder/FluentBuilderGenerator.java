/* 
 * Created on 03-12-2012 19:25:53 by aludwiko
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
	private String launchBuildMethodName = "build";
	private static FluentBuilderGeneratorPrinter printer = new FluentBuilderGeneratorPrinter(System.out);


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

	/**
	 * default is 'build'
	 * @param launchBuildMethodName - new method name for launching build
	 */
	public void withLaunchBuildMethodName(String launchBuildMethodName) {
		this.launchBuildMethodName = launchBuildMethodName;
	}

	/**
	 * print builder to {@link System#out}
	 */
	public void printBuilder() {
		printer.printComment(clazz.getName());
	}

	public void should() {

		// given
		// Joiner.on("as").skipNulls();
		FluentBuilderGenerator.forClass(String.class).printBuilder();

		// when

		// then
	}
}
