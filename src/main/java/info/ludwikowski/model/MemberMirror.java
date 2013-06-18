/*
 * Created on 09-03-2013 22:30:26 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;


public interface MemberMirror {

	/**
	 * full collection name like: java.util.Set
	 */
	String getCollectionType();

	/**
	 * simple field type like: Set<Client>
	 */
	String getSimpleType();

	/**
	 * filed name like: clients
	 */
	String getName();

	Imports getImports();

	boolean isSupporterVarargsCollection();

	/**
	 * collections element simple name like: Client
	 */
	String getCollectionElementSimpleName();
}
