/*
 * Created on 18 wrz 2014 19:00:26 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.inheritance;


public class Parent extends Gran {

	private String parentField;
	@SuppressWarnings("unused")
	private String _ignoredField;


	public String getParentField() {
		return parentField;
	}
}
