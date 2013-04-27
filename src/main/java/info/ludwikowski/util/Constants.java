/*
 * Created on 03-04-2013 17:53:37 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;

import java.util.LinkedList;
import java.util.List;


public class Constants {

	public static List<String> SUPPORETED_VARARGS_COLLECTIONS = new LinkedList<String>();

	static {
		SUPPORETED_VARARGS_COLLECTIONS.add(java.util.Set.class.getName());
		SUPPORETED_VARARGS_COLLECTIONS.add(java.util.List.class.getName());
	}
}
