/* 
 * Created on 11-03-2013 19:42:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import javax.lang.model.element.Element;


public class ClassVerifier {

	private final ProcessorContext context;


	public ClassVerifier(ProcessorContext context) {
		this.context = context;
	}

	public boolean shouldGenerateBuilder(Element element) {

		if (context.isAcceptJavaPersisentceAnnotations()){
			
		}
		
		
		return false;
	}

}
