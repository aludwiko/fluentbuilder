/* 
 * Created on 22-03-2013 20:42:27 by Andrzej Ludwikowski
 */

package info.ludwikowski.model;


public class MemberMirrorImpl implements MemberMirror {

	private String type;
	private String name;


	public MemberMirrorImpl(String type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

}
