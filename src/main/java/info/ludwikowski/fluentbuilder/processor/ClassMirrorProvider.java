/*
 * Created on 09-03-2013 22:26:19 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;

/**
 * This class generates ClassMirrors from given class elements for the
 * generator.
 * @author Andrzej Ludwikowski
 */
public class ClassMirrorProvider {

    private final ClassVerifier classVerifier;
    private final ProcessorContext context;

    /**
     * Default constructor for a ClassMirrorProvider.
     * @param classVerifier Verifier checks if for the current class a builder
     *            should be generated.
     * @param context inhabits settings for the {@link FluentBuilderProcessor}
     */
    public ClassMirrorProvider(final ClassVerifier classVerifier, final ProcessorContext context) {
        this.classVerifier = classVerifier;
        this.context = context;
    }

    /**
     * Checks all elements of a given Set for class Elements and creates for
     * each found class {@link Element} a ClassMirror.
     * @param elements Set of Elements which are checked for classes
     * @return a Collection of ClassMirrors which represent the found classes in
     *         elements
     */
    public final Collection<ClassMirror> prepareMirrors(final Set<? extends Element> elements) {

        final Set<ClassMirror> classMirrors = new HashSet<ClassMirror>();

        for (final Element element : elements) {

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
