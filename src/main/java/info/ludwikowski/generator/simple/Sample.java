/* 
 * Created on 09-12-2012 09:51:05 by Andrzej Ludwikowski
 */

package info.ludwikowski.generator.simple;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * sample class to show/check fluent builder
 * @author Andrzej Ludwikowski
 *
 */
public class Sample implements Serializable {

	private static final long serialVersionUID = 2631948252607310591L;
	private final String SOME_CONSTANCE = "SOME_CONSTANCE";

	private int id;
	private String name;
	private Map<String, List<Integer>> map;
}
