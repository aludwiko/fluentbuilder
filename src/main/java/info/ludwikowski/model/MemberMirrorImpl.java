/*
 * Created on 22-03-2013 20:42:27 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;

import info.ludwikowski.util.Constants;

import java.util.Set;

public class MemberMirrorImpl implements MemberMirror {

	private String simpleType;
	private String name;
	private Imports imports = new Imports();
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
		this.imports.addAll(imports);
	}

	@Override
	public boolean isSupporterVarargsCollection() {

		if (collectionType == null) {
			return false;
		}

		return Constants.SUPPORETED_VARARGS_COLLECTIONS.contains(collectionType);
	}

	@Override
	public Imports getImports() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collectionElementSimpleName == null) ? 0 : collectionElementSimpleName.hashCode());
		result = prime * result + ((collectionType == null) ? 0 : collectionType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((simpleType == null) ? 0 : simpleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MemberMirrorImpl other = (MemberMirrorImpl) obj;
		if (collectionElementSimpleName == null) {
			if (other.collectionElementSimpleName != null) {
				return false;
			}
		}
		else if (!collectionElementSimpleName.equals(other.collectionElementSimpleName)) {
			return false;
		}
		if (collectionType == null) {
			if (other.collectionType != null) {
				return false;
			}
		}
		else if (!collectionType.equals(other.collectionType)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		if (simpleType == null) {
			if (other.simpleType != null) {
				return false;
			}
		}
		else if (!simpleType.equals(other.simpleType)) {
			return false;
		}
		return true;
	}

}
