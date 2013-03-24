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



public class ClassMirrorProvider {

	private final ClassVerifier classVerifier;


	public ClassMirrorProvider(ClassVerifier classVerifier) {
		this.classVerifier = classVerifier;
	}

	public Collection<ClassMirror> prepareMirrors(Set<? extends Element> elements) {

		Set<ClassMirror> classMirrors = new HashSet<ClassMirror>();

		for (Element element : elements) {

			if (classVerifier.generateBuilder(element)) {

				classMirrors.add(new ClassMirrorImpl(element));
			}
		}

		return classMirrors;
	}

}
