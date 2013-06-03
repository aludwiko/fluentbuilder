/*
 * Created on 09-03-2013 22:30:26 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import java.util.Set;

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

	Set<String> getImports();

	boolean isSupporterVarargsCollection();

	/**
	 * collections element simple name like: Client
	 */
	String getCollectionElementSimpleName();
}
