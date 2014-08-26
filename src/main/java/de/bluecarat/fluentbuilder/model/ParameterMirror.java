/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.model;

import javax.lang.model.type.TypeMirror;

/**
 * This class is used to represent method parameters.
 * @author Jan van Esdonk
 */
public final class ParameterMirror {

    private final TypeMirror parameterType;
    private final String parameterName;

    /**
     * Creates a ParameterMirror with all necessary information.
     * @param parameterType Type of the given parameter
     * @param parameterName Name of the given parameter
     */
    public ParameterMirror(final TypeMirror parameterType, final String parameterName) {
        this.parameterType = parameterType;
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the represented parameter.
     * @return name of the represented parameter
     */
    public String getName() {
        return parameterName;
    }

    /**
     * Returns the type of the represented parameter.
     * @return type of the represented parameter as a {@link TypeMirror}
     */
    public TypeMirror getType() {
        return parameterType;
    }

    @Override
    public String toString() {
        return parameterType + " " + parameterName;
    }
}
