/* 
 * Created on 09-03-2013 22:26:19 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;

import fluentbuilder.model.ClassMirror;


public class ClassMirrorConverter {

	public Collection<ClassMirror> convert(Set<? extends Element> elements) {

		Set<ClassMirror> classMirrors = new HashSet<ClassMirror>();

		for (Element element : elements) {

		}

		return classMirrors;
	}

}
