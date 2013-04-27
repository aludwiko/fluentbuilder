/*
 * Created on 09-03-2013 22:30:26 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import java.util.Set;

public interface MemberMirror {

	/**
	 * java.util.Set
	 * 
	 * @return
	 */
	String getCollectionType();

	/**
	 * private Set<Client> clients => Set<Client>
	 * 
	 * @return
	 */
	String getSimpleType();

	/**
	 * private Set<Client> clients => clients
	 * 
	 * @return
	 */
	String getName();

	Set<String> getImports();

	boolean isSupporterVarargsCollection();

	/**
	 * e.q. Client
	 * 
	 * @return
	 */
	String getCollectionElementSimpleName();
}
