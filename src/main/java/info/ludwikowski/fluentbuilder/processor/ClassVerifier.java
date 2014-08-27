/*
 * Created on 11-03-2013 19:42:45 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import javax.lang.model.element.Element;

import de.bluecarat.fluentbuilder.annotation.GenerateBuilder;

/**
 * This class looks for {@link GenerateBuilder} Annotations in a given
 * {@link Element} to verify that a Builder should be generated for that
 * Element.
 * @author Andrzej Ludwikowski
 */
public class ClassVerifier {

    private final ProcessorContext context;

    /**
     * Default constructor for a ClassVerifier.
     * @param context inhabits the settings for the ClassVerifier
     */
    public ClassVerifier(final ProcessorContext context) {
        this.context = context;
    }

    /**
     * Checks if a {@links GenerateBuilder} Annotation is found in the element.
     * @param element which is checked for a GenerateBuilder Annotation
     * @return True if the annotation is found, false if not
     */
    public final boolean generateBuilder(final Element element) {

        if (info.ludwikowski.fluentbuilder.util.TypeUtils.containsAnnotation(
                element,
                GenerateBuilder.class.getCanonicalName())) {
            return true;
        }

        if (context.isAcceptJavaPersistenceAnnotations()
                && info.ludwikowski.fluentbuilder.util.TypeUtils.containsAnnotation(
                        element,
                        info.ludwikowski.fluentbuilder.processor.ProcessorContext.JPA_ANNOTATIONS)) {
            return true;
        }

        return false;
    }

}
