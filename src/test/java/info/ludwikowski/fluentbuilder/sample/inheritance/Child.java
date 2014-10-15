/*
 * Created on 18 wrz 2014 19:00:54 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.sample.inheritance;

import info.ludwikowski.fluentbuilder.annotation.GenerateBuilder;

@GenerateBuilder
public class Child extends Parent {

	private String childField;


	public String getChildField() {
		return childField;
	}
}
