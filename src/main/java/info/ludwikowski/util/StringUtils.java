/* 
 * Created on 19-03-2013 17:03:58 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;


public class StringUtils {

	public static boolean hasText(String staticCreateMethodName) {
		return staticCreateMethodName != null && !staticCreateMethodName.trim().equals("");
	}

}
