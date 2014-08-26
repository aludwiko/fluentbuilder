/*
 * Created on 09-03-2013 12:51:40 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import java.util.Collection;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import info.ludwikowski.fluentbuilder.model.ClassMirror;

/**
 * This class contains the main annotation processing logic for the builder
 * generation.
 * @author Andrzej Ludwikowski
 */
@SupportedAnnotationTypes({ info.ludwikowski.fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_ENTITY,
        info.ludwikowski.fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_MAPPEDSUPERCLASS,
        info.ludwikowski.fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_EMBEDDABLE,
        info.ludwikowski.fluentbuilder.processor.ProcessorContext.FLUENT_BUILDER_ANNOTATATION })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class FluentBuilderProcessor extends AbstractProcessor {

    private ProcessorContext context;
    private ClassMirrorProvider classMirrorProvider;
    private ClassWriter classWriter;

    @Override
    public final SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public final synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        context = new ProcessorContext(processingEnv);
        final ClassVerifier classVerifier = new ClassVerifier(context);
        classMirrorProvider = new ClassMirrorProvider(classVerifier, context);
        classWriter = new ClassWriter(context);
        context.logConfiguration();
    }

    @Override
    public final boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        final Set<? extends Element> elements = roundEnv.getRootElements();

        final Collection<ClassMirror> classMirrors = classMirrorProvider.prepareMirrors(elements);

        classWriter.write(classMirrors);

        return false;
    }

}
