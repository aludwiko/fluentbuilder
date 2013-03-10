/* 
 * Created on 09-03-2013 12:51:40 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

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
		"javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embeddable"
})
@SupportedOptions({

})
public class FluentBuilderProcessor extends AbstractProcessor {

	private ProcessorContext context;
	private ClassMirrorConverter classMirrorConverter;
	private ClassWriter classWriter;


	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		context = new ProcessorContext(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		Set<? extends Element> elements = roundEnv.getRootElements();
		
		Collection<ClassMirror> classMirrors = classMirrorConverter.convert(elements);

		classWriter.write(context, classMirrors);
		
		return false;
	}


}
