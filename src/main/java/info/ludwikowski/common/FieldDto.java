/*
 * Created on 21-12-2012 20:37:00 by Andrzej Ludwikowski
 */

package info.ludwikowski.common;



public class FieldDto {

	private String name;
	private String type;
	private Class<?> collection;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collection == null) ? 0 : collection.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldDto other = (FieldDto) obj;
		if (collection == null) {
			if (other.collection != null)
				return false;
		}
		else if (!collection.equals(other.collection))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		}
		else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return type + " " + name;
	}

	public FieldDto(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public FieldDto(String name, String type, Class<?> collection) {
		this(name, type);
		this.collection = collection;
	}

	public Class<?> getCollection() {
		return collection;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
