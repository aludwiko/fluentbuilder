/*
 * Created on 11-03-2013 19:40:09 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * This utility class provides tools which are useful for checking the
 * properties of a given type or element. These properties include annotations,
 * static and final declarations and a check for types of collections and
 * generics.
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public final class TypeUtils {

    private static final String LIST_PACKAGE_REGEX = "java\\.util\\.(Array)*List(<.*>)?";
    private static final String SET_PACKAGE_REGEX = "java\\.util\\.(Hash)*Set(<.*>)?";

    private TypeUtils() {

    }

    /**
     * Checks if a given {@link Element} contains an annotation.
     * @param element element to check for annotations
     * @param annotations name of annotation to check for
     * @return True if Element contains given annotation, false if not.
     */
    public static boolean containsAnnotation(final Element element, final String... annotations) {
        assert element != null;
        assert annotations != null;

        final List<String> annotationClassNames = new ArrayList<String>();
        Collections.addAll(annotationClassNames, annotations);

        return containsAnnotation(element, annotationClassNames);
    }

    /**
     * Checks if a given {@link Element} contains an annotation.
     * @param element element to check for annotations
     * @param annotationClassNames name of annotation to check for
     * @return True if Element contains given annotation, false if not.
     */
    public static boolean containsAnnotation(final Element element, final Collection<String> annotationClassNames) {

        final List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
        for (final AnnotationMirror mirror : annotationMirrors) {
            if (annotationClassNames.contains(mirror.getAnnotationType().toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a type is a Set.
     * @param type which will be checked as a String
     * @return true if the given type is a Set. false if it is not.
     */
    public static boolean isSet(final String type) {
        return type.matches(SET_PACKAGE_REGEX);
    }

    /**
     * Checks if a type is a List.
     * @param type which will be checked as a String
     * @return true if the given type is a List. false if it is not.
     */
    public static boolean isList(final String type) {
        return type.matches(LIST_PACKAGE_REGEX);
    }

    /**
     * Checks if a type is a Set or List.
     * @param type which will be checked as a String
     * @return true if the given type is a List or Set. false if it is neither.
     */
    public static boolean isListOrSet(final String type) {
        return isList(type) || isSet(type);
    }

    /**
     * Checks if a field is static or final.
     * @param field - this field which is given as an Element will be checked.
     * @return true if the field is static or final. false if the field is
     *         neither.
     */
    public static boolean isStaticOrFinal(final Element field) {
        return isStatic(field) || isFinal(field);
    }

    private static boolean isFinal(final Element field) {
        return field.getModifiers().contains(Modifier.FINAL);
    }

    private static boolean isStatic(final Element field) {
        return field.getModifiers().contains(Modifier.STATIC);
    }

    /**
     * Checks if a field is static or final.
     * @param field - this field which is given as a Field will be checked.
     * @return true if the field is static or final. false if the field is
     *         neither.
     */
    public static boolean isStaticOrFinal(final Field field) {

        final int modifiers = field.getModifiers();

        return java.lang.reflect.Modifier.isFinal(modifiers) || java.lang.reflect.Modifier.isStatic(modifiers);
    }

    /**
     * Checks if a given field has a generic type.
     * @param field - this field will be checked.
     * @return true for a field with a generic type. false for a field without a
     *         generic type.
     */
    public static boolean isGeneric(final Field field) {

        final Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType) {

            final ParameterizedType parameterizedType = (ParameterizedType) genericType;

            return parameterizedType.getActualTypeArguments().length > 0;
        }

        return false;
    }

    /**
     * Checks if a type is a primitive type or not.
     * @param type - given as a string
     * @return true for a primitive type, false for a normal type.
     */
    public static boolean isPrimitiveType(final String type) {
        final Set<String> primitiveTypes = new HashSet<String>();
        primitiveTypes.add("int");
        primitiveTypes.add("char");
        primitiveTypes.add("float");
        primitiveTypes.add("double");
        primitiveTypes.add("byte");
        primitiveTypes.add("short");
        primitiveTypes.add("long");
        primitiveTypes.add("boolean");

        if (primitiveTypes.contains(type)) {
            return true;
        }
        return false;
    }

}
