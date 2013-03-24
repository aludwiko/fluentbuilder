/* 
 * Created on 19-03-2013 17:03:58 by Andrzej Ludwikowski
 */

package info.ludwikowski.util;



public class StringUtils {

	public static boolean hasText(String staticCreateMethodName) {
		return staticCreateMethodName != null && !staticCreateMethodName.trim().equals("");
	}

	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0)))
										.append(str.substring(1))
										.toString();
	}

}
