/*
 * Created on 22-03-2013 20:42:27 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import info.ludwikowski.util.Constants;

import java.util.Set;
import java.util.TreeSet;

public class MemberMirrorImpl implements MemberMirror {

	private String simpleType;
	private String name;
	private Set<String> imports = new TreeSet<String>();
	private String collectionType;
	private String collectionElementSimpleName;


	public static MemberMirror collectionMirror(String name, String simpleType, Set<String> imports, String collectionType, String collectionElementSimpleName) {
		return new MemberMirrorImpl(name, simpleType, imports, collectionType, collectionElementSimpleName);
	}

	public static MemberMirror simpleMirror(String name, String simpleType, Set<String> imports) {
		return new MemberMirrorImpl(name, simpleType, imports, null, null);
	}

	private MemberMirrorImpl(String name, String simpleType, Set<String> imports, String collectionType, String collectionElementSimpleName) {
		this.name = name;
		this.collectionType = collectionType;
		this.simpleType = simpleType;
		this.collectionElementSimpleName = collectionElementSimpleName;
		if (imports != null) {
			this.imports.addAll(imports);
		}
	}

	@Override
	public boolean isSupporterVarargsCollection() {

		if (collectionType == null) {
			return false;
		}

		return Constants.SUPPORETED_VARARGS_COLLECTIONS.contains(collectionType);
	}

	@Override
	public Set<String> getImports() {
		return imports;
	}

	@Override
	public String getSimpleType() {
		return simpleType;
	}

	@Override
	public String getCollectionType() {
		return collectionType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCollectionElementSimpleName() {
		return collectionElementSimpleName;
	}

}
