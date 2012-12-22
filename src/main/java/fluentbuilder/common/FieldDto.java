/* 
 * Created on 21-12-2012 20:37:00 by Andrzej Ludwikowski
 */

package fluentbuilder.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class FieldDto {

	private String name;
	private String type;


	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (!(obj instanceof FieldDto))
			return false;

		FieldDto fieldDto = (FieldDto) obj;

		return new EqualsBuilder().append(name, fieldDto.name)
									.append(type, fieldDto.type).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(type).toHashCode();
	}

	@Override
	public String toString() {
		return type + " " + name;
	}

	public FieldDto(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
