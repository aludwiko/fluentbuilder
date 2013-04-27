/*
 * Created on 11-03-2013 19:40:09 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;

import static info.ludwikowski.util.StringUtils.nullToEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;

public class TypeUtils {

	private static final String PACKAGE_REGEXP = "([a-z]*\\.)";


	public static boolean containsAnnotation(Element element, String... annotations) {
		assert element != null;
		assert annotations != null;

		List<String> annotationClassNames = new ArrayList<String>();
		Collections.addAll(annotationClassNames, annotations);

		return containsAnnotation(element, annotationClassNames);
	}

	public static boolean containsAnnotation(Element element, Collection<String> annotationClassNames) {

		List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
		for (AnnotationMirror mirror : annotationMirrors) {
			if (annotationClassNames.contains(mirror.getAnnotationType().toString())) {
				return true;
			}
		}
		return false;
	}

	public static String simpleType(String string) {
		return string.replaceAll(PACKAGE_REGEXP, "");
	}

	public static boolean isSet(String type) {
		return Set.class.getName().equals(nullToEmpty(type));
	}

	public static boolean isList(String type) {
		return List.class.getName().equals(nullToEmpty(type));
	}

	public static boolean isListOrSet(String type) {
		return isList(type) || isSet(type);
	}
}
