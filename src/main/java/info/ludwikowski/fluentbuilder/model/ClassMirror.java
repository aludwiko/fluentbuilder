/*
 * Created on 09-03-2013 22:26:53 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * A ClassMirror provides a representation of a Java class. It stores
 * information about its member fields, constructors, package and imports.
 * 
 * @author Andrzej Ludwikowski
 */
public interface ClassMirror {

	/**
	 * Returns the simple name of the represented class.
	 * 
	 * @return simple name of the represented class
	 */
	String getSimpleName();

	/**
	 * Returns the package name of the represented class.
	 * 
	 * @return package name of the represented class
	 */
	String getPackageName();

	/**
	 * Returns the members of the class which includes.
	 * 
	 * @return all members of the represented class as a list
	 */
	List<MemberMirror> getMembers();

	/**
	 * Returns the the necessary imports of the class.
	 * 
	 * @return all necessary imports as a set.
	 */
	Set<String> getImports();

	/**
	 * Returns the the necessary imports only for constructors in the class.
	 * 
	 * @return all necessary imports as a set.
	 */
	Set<String> getOnlyConstructorsImports();

	/**
	 * Returns the members of the class which are collections and therefore need
	 * VarArg setters.
	 * 
	 * @return all members of the represented class which are collection fields
	 *         as a list
	 */
	List<MemberMirror> getVarArgsMembers();

	/**
	 * Returns the constructors of the class.
	 * VarArg setters.
	 * 
	 * @return all members of the represented class which are collection fields
	 *         as a list
	 */
	Collection<Constructor> getConstructors();
}
