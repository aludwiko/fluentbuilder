/*
 * Created on 09-03-2013 22:30:26 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

/**
 * A MemberMirror contains information about a class member. It can represent
 * methods, constructors and fields.
 * 
 * @author Andrzej Ludwikowski
 */
public interface MemberMirror {

	/**
	 * Returns the full collection name if the current MemberMirror represents a
	 * field of a collection type.
	 * 
	 * @return the full collection type name from the represented field as a
	 *         String
	 */
	String getCollectionType();

	/**
	 * Returns the simple field type if the MemberMirror represents a field.
	 * 
	 * @return the simple type of the represented field as a String
	 */
	String getSimpleType();

	/**
	 * Returns the name of the represented field or method.
	 * 
	 * @return name of the represented field or method
	 */
	String getName();

	/**
	 * Returns the import which are needed for this member encapsulated in an {@link Imports} object.
	 * 
	 * @return the needed imports as a {@link Imports} object.
	 */
	Imports getImports();

	/**
	 * Checks if the represented member is a collection and if its type is
	 * supported for VarArgs setter creation by the FluentBuilderGenerator.
	 * 
	 * @return True if the MemberMirror is a field of a collection type which is
	 *         supported, false if not.
	 */
	boolean isSupportedVarArgsCollection();

	/**
	 * Returns the collections element simple name like: Client.
	 * 
	 * @return collections element simple name like: Client.
	 */
	String getCollectionElementSimpleName();

	/**
	 * Returns the fully qualified name of the class which owns the represented
	 * member.
	 * 
	 * @return the fully qualified name of the owner
	 */
	String getOwnerName();

	/**
	 * Returns the simple name of the class which owns the represented member.
	 * 
	 * @return the simple name of the owner
	 */
	String getSimpleOwnerName();
}
