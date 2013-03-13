/* 
 * Created on 09-03-2013 22:26:19 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;

import fluentbuilder.model.ClassMirror;


public class ClassMirrorProvider {

	private final ClassVerifier classVerifier;


	public ClassMirrorProvider(ClassVerifier classVerifier) {
		this.classVerifier = classVerifier;
	}

	public Collection<ClassMirror> prepareMirrors(Set<? extends Element> elements) {

		Set<ClassMirror> classMirrors = new HashSet<ClassMirror>();

		for (Element element : elements) {

			if (classVerifier.shouldGenerateBuilder(element)) {

			}

		}

		return classMirrors;
	}

}
