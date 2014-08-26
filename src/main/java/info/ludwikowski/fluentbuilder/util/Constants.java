/*
 * Created on 03-04-2013 17:53:37 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class provides constants for the FluentBuilderGenerator.
 * @author Andrzej Ludwikowski
 */
public final class Constants {

    public static final List<String> SUPPORTED_VARARGS_COLLECTIONS = new LinkedList<String>();

    static {
        SUPPORTED_VARARGS_COLLECTIONS.add(Set.class.getName());
        SUPPORTED_VARARGS_COLLECTIONS.add(List.class.getName());
    }

    private Constants() {

    }

}
