/* 
 * Created on 09-03-2013 12:51:40 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import static fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_EMBEDDABLE;
import static fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_ENTITY;
import static fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_MAPPEDSUPERCLASS;

import java.util.Collection;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import fluentbuilder.model.ClassMirror;


@SupportedAnnotationTypes({
		JAVAX_PERSISTENCE_ENTITY, JAVAX_PERSISTENCE_MAPPEDSUPERCLASS, JAVAX_PERSISTENCE_EMBEDDABLE
})
@SupportedOptions({

})
public class FluentBuilderProcessor extends AbstractProcessor {

	private ProcessorContext context;
	private ClassMirrorProvider classMirrorProvider;
	private ClassWriter classWriter;


	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		context = new ProcessorContext(processingEnv);
		classMirrorProvider = new ClassMirrorProvider(new ClassVerifier(context));
		classWriter = new ClassWriter(context);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		Set<? extends Element> elements = roundEnv.getRootElements();
		
		Collection<ClassMirror> classMirrors = classMirrorProvider.prepareMirrors(elements);

		classWriter.write(classMirrors);
		
		return false;
	}


}
