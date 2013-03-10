/* 
 * Created on 09-03-2013 13:04:57 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import javax.annotation.processing.ProcessingEnvironment;


public class ProcessorContext {

	public static final String METHOD_PREFIX = "methodPrefix";
	public static final String BUILDER_CLASS_POSTFIX = "builderClassPostfix";
	public static final String BUILDER_CLASS_PREFIX = "builderClassPrefix";
	public static final String GENERATE_STATIC_CREATE_METHOD = "generateStaticCreateMethod";
	public static final String GENERATE_VARARGS_FOR_COLLECTIONS = "generateVarargsForCollections";

	private String methodPrefix = "with";
	private String builderClassPostfix = "Builder";
	private String abstractBuilderClassPrefix = "Abstract";
	private boolean staticCreate = true;
	private boolean varargsForCollections = true;


	public ProcessorContext(ProcessingEnvironment processingEnv) {
		// TODO Auto-generated constructor stub
	}

	public String getAbstractBuilderClassPrefix() {
		return abstractBuilderClassPrefix;
	}

	public String getBuilderClassPostfix() {
		return builderClassPostfix;
	}

}
