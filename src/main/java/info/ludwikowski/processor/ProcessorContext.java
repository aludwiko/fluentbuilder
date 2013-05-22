/*
 * Created on 09-03-2013 13:04:57 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;


public class ProcessorContext {

	public static final String METHOD_PREFIX = "methodPrefix";
	public static final String BUILDER_CLASS_POSTFIX = "builderClassPostfix";
	public static final String BUILDER_CLASS_PREFIX = "builderClassPrefix";
	public static final String GENERATE_STATIC_CREATE_METHOD = "generateStaticCreateMethod";
	public static final String GENERATE_VARARGS_FOR_COLLECTIONS = "generateVarargsForCollections";
	public static final String ACCEPT_JAVA_PERSISTENCE_ANNOTATIONS = "acceptJavaPersisentceAnnotations";
	public static final String DEBUG = "debug";

	public static final String JAVAX_PERSISTENCE_ENTITY = "javax.persistence.Entity";
	public static final String JAVAX_PERSISTENCE_MAPPEDSUPERCLASS = "javax.persistence.MappedSuperclass";
	public static final String JAVAX_PERSISTENCE_EMBEDDABLE = "javax.persistence.Embeddable";
	public static final String FLUENT_BUILDER_ANNOTATATION = "info.ludwikowski.processor.GenerateBuilder";

	public static final List<String> JPA_ANNOTATIONS;

	static {
		List<String> jpaAnnotations = new ArrayList<String>(3);
		jpaAnnotations.add(JAVAX_PERSISTENCE_ENTITY);
		jpaAnnotations.add(JAVAX_PERSISTENCE_MAPPEDSUPERCLASS);
		jpaAnnotations.add(JAVAX_PERSISTENCE_EMBEDDABLE);
		JPA_ANNOTATIONS = Collections.unmodifiableList(jpaAnnotations);
	}

	private String methodPrefix = "with";
	private String builderClassPostfix = "Builder";
	private String abstractBuilderClassPrefix = "Abstract";
	private String staticCreateMethodName = "create";
	private boolean acceptJavaPersisentceAnnotations = false;
	private boolean staticCreate = true;
	private boolean varargsForCollections = true;
	private final ProcessingEnvironment processingEnv;
	private boolean debug = false;


	public ProcessorContext(ProcessingEnvironment processingEnv) {
		this.processingEnv = processingEnv;
		this.debug = Boolean.parseBoolean(processingEnv.getOptions().get(DEBUG));
	}
	
	public String getMethodPrefix() {
		return methodPrefix;
	}

	public String getStaticCreateMethodName() {
		return staticCreateMethodName;
	}

	public boolean isStaticCreate() {
		return staticCreate;
	}

	public boolean isAcceptJavaPersisentceAnnotations() {
		return acceptJavaPersisentceAnnotations;
	}

	public String getAbstractBuilderClassPrefix() {
		return abstractBuilderClassPrefix;
	}

	public String getBuilderClassPostfix() {
		return builderClassPostfix;
	}

	public Elements getElementUtils() {
		return processingEnv.getElementUtils();
	}

	public Types getTypeUtils() {
		return processingEnv.getTypeUtils();
	}

	public ProcessingEnvironment getProcessingEnvironment() {
		return processingEnv;
	}

	public void logInfo(String message) {
		log(Kind.NOTE, message);
	}

	private void log(Kind kind, String message) {
		if (debug) {
			processingEnv.getMessager().printMessage(kind, message);
		}
	}

	public void logError(String message) {
		log(Kind.ERROR, message);
	}

	public void logConfiguration() {
		processingEnv.getMessager().printMessage(Kind.NOTE, toString());
	}

	@Override
	public String toString() {
		return "ProcessorContext [methodPrefix=" + methodPrefix + ", builderClassPostfix=" + builderClassPostfix + ", abstractBuilderClassPrefix=" + abstractBuilderClassPrefix
				+ ", staticCreateMethodName=" + staticCreateMethodName + ", acceptJavaPersisentceAnnotations=" + acceptJavaPersisentceAnnotations + ", staticCreate="
				+ staticCreate + ", varargsForCollections=" + varargsForCollections + ", processingEnv=" + processingEnv + ", debug=" + debug + "]";
	}

}
