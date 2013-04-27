/*
 * Created on 09-03-2013 22:26:19 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.model.ClassMirrorImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;


public class ClassMirrorProvider {

	private final ClassVerifier classVerifier;
	private final ProcessorContext context;


	public ClassMirrorProvider(ClassVerifier classVerifier, ProcessorContext context) {
		this.classVerifier = classVerifier;
		this.context = context;
	}

	public Collection<ClassMirror> prepareMirrors(Set<? extends Element> elements) {

		Set<ClassMirror> classMirrors = new HashSet<ClassMirror>();

		for (Element element : elements) {

			if (!ElementKind.CLASS.equals(element.getKind())) {
				continue;
			}

			if (classVerifier.generateBuilder(element)) {

				classMirrors.add(new ClassMirrorImpl(element, context));
			}
		}

		return classMirrors;
	}

}
