/* 
 * Created on 11-03-2013 19:42:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static info.ludwikowski.processor.ProcessorContext.JPA_ANNOTATIONS;
import static info.ludwikowski.util.TypeUtils.containsAnnotation;
import info.ludwikowski.annotation.GenerateBuilder;

import javax.lang.model.element.Element;


public class ClassVerifier {

	private final ProcessorContext context;


	public ClassVerifier(ProcessorContext context) {
		this.context = context;
	}

	public boolean generateBuilder(Element element) {

		if (containsAnnotation(element, GenerateBuilder.class.getCanonicalName())) {
			return true;
		}
		
		if (context.isAcceptJavaPersisentceAnnotations() && containsAnnotation(element, JPA_ANNOTATIONS)) {
			return true;
		}
		
		return false;
	}

}
