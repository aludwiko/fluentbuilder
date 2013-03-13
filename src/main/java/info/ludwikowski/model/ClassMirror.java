/* 
 * Created on 09-03-2013 22:26:53 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import java.util.List;


public interface ClassMirror {

	String getSimpleName();

	String getPackageName();

	List<MemberMirror> getMembers();

	String getImports();
}
