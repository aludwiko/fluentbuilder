/*
 * Created on 09-03-2013 13:04:57 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import info.ludwikowski.fluentbuilder.common.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

import org.apache.commons.lang.StringUtils;

/**
 * This class provides settings for the annotation processor and builder
 * generation.
 * 
 * @author Andrzej Ludwikowski
 */
public class ProcessorContext extends Context {

	public static final String METHOD_PREFIX = "methodPrefix";
	public static final String BUILD_METHOD_NAME = "buildMethodName";
	public static final String BUILDER_CLASS_POSTFIX = "builderClassPostfix";
	public static final String BUILDER_CLASS_PREFIX = "builderClassPrefix";
	public static final String STATIC_CREATE_METHOD_NAME = "staticCreateMethodName";
	public static final String GENERATE_STATIC_CREATE_METHOD = "generateStaticCreateMethod";
	public static final String GENERATE_VARARGS_FOR_COLLECTIONS = "generateVarargsForCollections";
	public static final String ACCEPT_JAVA_PERSISTENCE_ANNOTATIONS = "acceptJavaPersistenceAnnotations";
	public static final String IGNORED_CLASS_PREFIX = "ignoredClassPrefix";
	public static final String USE_INDEFINITE_ARTICLES = "useIndefiniteArticles";
	public static final String DEBUG = "debug";

	public static final String JAVAX_PERSISTENCE_ENTITY = "javax.persistence.Entity";
	public static final String JAVAX_PERSISTENCE_MAPPEDSUPERCLASS = "javax.persistence.MappedSuperclass";
	public static final String JAVAX_PERSISTENCE_EMBEDDABLE = "javax.persistence.Embeddable";
	public static final String FLUENT_BUILDER_ANNOTATATION = "info.ludwikowski.fluentbuilder.annotation.GenerateBuilder";

	public static final List<String> JPA_ANNOTATIONS;

	static {
		final List<String> jpaAnnotations = new ArrayList<String>(3);
		jpaAnnotations.add(JAVAX_PERSISTENCE_ENTITY);
		jpaAnnotations.add(JAVAX_PERSISTENCE_MAPPEDSUPERCLASS);
		jpaAnnotations.add(JAVAX_PERSISTENCE_EMBEDDABLE);
		JPA_ANNOTATIONS = Collections.unmodifiableList(jpaAnnotations);
	}

	private boolean debug = false;
	private boolean acceptJavaPersistenceAnnotations = false;
	private final ProcessingEnvironment processingEnv;


	/**
	 * Default constructor for ProcessorContext. Sets all settings to their
	 * default values.
	 * 
	 * @param processingEnv the ProcessingEnvironment which is provided by the
	 *            Java Annotation Processor
	 */
	public ProcessorContext(final ProcessingEnvironment processingEnv) {
		System.out.println(processingEnv.getOptions());
		final Map<String, String> options = OptionsSplitter.splitOptions(processingEnv.getOptions());
		System.out.println(options);
		this.processingEnv = processingEnv;
		this.debug = getBoolean(options, DEBUG, false);
		this.acceptJavaPersistenceAnnotations = getBoolean(options, ACCEPT_JAVA_PERSISTENCE_ANNOTATIONS, false);
		setAbstractBuilderClassPrefix(options.get(BUILDER_CLASS_PREFIX));
		setBuilderClassPostfix(options.get(BUILDER_CLASS_POSTFIX));
		setBuildMethodName(options.get(BUILD_METHOD_NAME));
		setMethodPrefix(options.get(METHOD_PREFIX));
		setStaticCreate(getBoolean(options, GENERATE_STATIC_CREATE_METHOD, true));
		setStaticCreateMethodName(options.get(STATIC_CREATE_METHOD_NAME));
		setVarArgsForCollections(getBoolean(options, GENERATE_VARARGS_FOR_COLLECTIONS, true));
		setIgnoredClassPrefix(options.get(IGNORED_CLASS_PREFIX));
		setUseIndefiniteArticles(getBoolean(options, USE_INDEFINITE_ARTICLES, true));
	}

	private boolean getBoolean(final Map<String, String> options, final String key, final boolean defaultValue) {

		final String option = options.get(key);

		if (StringUtils.isNotBlank(option)) {
			return java.lang.Boolean.parseBoolean(option);
		}

		return defaultValue;
	}

	/**
	 * Returns an implementation of some utility methods for operating on
	 * elements.
	 * 
	 * @return element utilities
	 */
	public final Elements getElementUtils() {
		return processingEnv.getElementUtils();
	}

	/**
	 * Returns an implementation of some utility methods for operating on types.
	 * 
	 * @return type utilities
	 */
	public Types getTypeUtils() {
		return processingEnv.getTypeUtils();
	}

	/**
	 * Returns the given {@link ProcessingEnvironment}.
	 * 
	 * @return ProcessingEnvironment
	 */
	public final ProcessingEnvironment getProcessingEnvironment() {
		return processingEnv;
	}

	/**
	 * Logs a given message as a note.
	 * 
	 * @param message to log
	 */
	public final void logInfo(final String message) {
		log(Kind.NOTE, message);
	}

	private void log(final Kind kind, final String message) {
		if (debug) {
			processingEnv.getMessager().printMessage(kind, message);
		}
	}

	/**
	 * Logs a given message as an error.
	 * 
	 * @param message to log
	 */
	public final void logError(final String message) {
		log(Kind.ERROR, message);
	}

	/**
	 * Prints the ProcessorContext configuration.
	 */
	protected final void logConfiguration() {
		processingEnv.getMessager().printMessage(Kind.NOTE, toString());
	}

	/**
	 * Checks if the ProcessorContext is configured to accept Java Persistence
	 * Annotations.
	 * 
	 * @return True if Java Persistence Annotations are accepted, false if not.
	 */
	public boolean isAcceptJavaPersistenceAnnotations() {
		return acceptJavaPersistenceAnnotations;
	}

	@Override
	public final String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ProcessorContext [debug=");
		builder.append(debug);
		builder.append(", acceptJavaPersisentceAnnotations=");
		builder.append(acceptJavaPersistenceAnnotations);
		builder.append(", processingEnv=");
		builder.append(processingEnv);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
