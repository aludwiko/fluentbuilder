/*
 * Created on 2 paź 2014 20:35:36 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.model;


public class ConstructorParam implements Comparable<ConstructorParam> {

	private String name;
	private String type;


	public ConstructorParam(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(ConstructorParam o) {
		return type.compareTo(o.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConstructorParam [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
}
