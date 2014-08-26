/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker interface for Methods which are used to find the correct fields when
 * building a new object. This annotation provides the full field name for a
 * Builder set-method to guarantee that the correct field is selected and no
 * conflicts with hidden fields occur. It is created for every member field when
 * generating a AbstractBuilder class.
 * @author Jan van Esdonk
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReferencedField {
    /**
     * The full field name of the field which will be set by the annotated
     * setter method.
     */
    String value();
}
