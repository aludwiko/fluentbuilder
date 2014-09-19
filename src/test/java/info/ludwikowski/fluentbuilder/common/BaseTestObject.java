/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

/**
 * @author Jan van Esdonk
 */
public class BaseTestObject {

	private int intField;
	private String stringField;
	private char charField;
	private int sameField;
	private int uniqueBaseField;


	public BaseTestObject() {}

	public BaseTestObject(int intField, String stringField, char charField, int sameField, int uniqueBaseField) {
		this.intField = intField;
		this.stringField = stringField;
		this.charField = charField;
		this.sameField = sameField;
		this.uniqueBaseField = uniqueBaseField;
	}

	public int getUniqueBaseField() {
		return uniqueBaseField;
	}

	public int getDuplicatedFieldfromBase() {
		return sameField;
	}

	public int getIntField() {
		return intField;
	}

	public String getStringField() {
		return stringField;
	}


	public char getCharField() {
		return charField;
	}

	public void setCharField(char charField) {
		this.charField = charField;
	}
}
