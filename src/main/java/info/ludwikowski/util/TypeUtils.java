/*
 * Created on 11-03-2013 19:40:09 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;

import static info.ludwikowski.util.StringUtils.nullToEmpty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

public class TypeUtils {

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

	public static boolean isSet(String type) {
		return Set.class.getName().equals(nullToEmpty(type));
	}

	public static boolean isList(String type) {
		return List.class.getName().equals(nullToEmpty(type));
	}

	public static boolean isListOrSet(String type) {
		return isList(type) || isSet(type);
	}

	public static boolean isStaticOrFinal(Element field) {
		return isStatic(field) || isFinal(field);
	}

	private static boolean isFinal(Element field) {
		return field.getModifiers().contains(Modifier.FINAL);
	}

	private static boolean isStatic(Element field) {
		return field.getModifiers().contains(Modifier.STATIC);
	}

	public static boolean isStaticOrFinal(Field field) {
		
		int modifiers = field.getModifiers();

		return java.lang.reflect.Modifier.isFinal(modifiers)
				|| java.lang.reflect.Modifier.isStatic(modifiers);
	}
}
