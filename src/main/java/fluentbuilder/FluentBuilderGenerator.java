/*
 * Created on 03-12-2012 19:25:53 by Andrzej Ludwikowski
 */

package fluentbuilder;



/**
 * 
 * @author Andrzej Ludwikowski
 */
public class FluentBuilderGenerator {

 //toto
	private Class<?> clazz;
	private String methodPrefix = "with";
	private String builderName = "builder";
	private String launchBuildMethodName = "build";
	private static FluentBuilderGeneratorPrinter printer = new FluentBuilderGeneratorPrinter(System.out);
	private static FluentBuilderFieldProvider fieldProvider = new FluentBuilderFieldProvider();


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
	 * @return 
	 */
	public FluentBuilderGenerator withMethodPrefix(String methodPrefix) {
		this.methodPrefix = methodPrefix;
		return this;
	}

	/**
	 * default is 'builder'
	 * @param methodPrefix - new builder name (camelcase notation)
	 * @return 
	 */
	public FluentBuilderGenerator withBuilderName(String builderName) {
		this.builderName = builderName;
		return this;
	}

	/**
	 * default is 'build'
	 * @param launchBuildMethodName - new method name for launching build
	 * @return 
	 */
	public FluentBuilderGenerator withLaunchBuildMethodName(String launchBuildMethodName) {
		this.launchBuildMethodName = launchBuildMethodName;
		return this;
	}

	/**
	 * print builder to {@link System#out}
	 */
	public void printBuilder() {
		printer.printComment(clazz.getSimpleName());
		printer.printBuilderStaticInvocation(builderName);
		printer.printBuilderBegin(clazz.getSimpleName(), builderName);
		printer.printBuilderBody(fieldProvider.findProperFields(clazz), builderName, clazz.getSimpleName(), methodPrefix);
		printer.printBuilderEnd(clazz.getSimpleName(), launchBuildMethodName);
	}
}
