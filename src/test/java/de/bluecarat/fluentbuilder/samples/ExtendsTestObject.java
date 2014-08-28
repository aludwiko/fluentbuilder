/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import java.util.List;

/**
 * @author Jan van Esdonk
 */
@SuppressWarnings("unused")
public class ExtendsTestObject extends BaseTestObject {

	private int sameField;
	private int uniqueExtendsField;
	private int duplicateInSuperAndBase;
	private int duplicateInSuperSuperAndSuper;
	private int BaseTestObjectNameConflict;
	private int BaseTestObjectBaseTestObjectNameConflict;
	private int NameConflict;
	private List<String> duplicatedCollection;


	public ExtendsTestObject(int intField, String stringField, char charField, int sameField, int uniqueBase,
			int sameField2, int uniqueExtendsField) {
		super(intField, stringField, charField, sameField, uniqueBase);
		sameField = sameField2;
		this.uniqueExtendsField = uniqueExtendsField;
	}

	public int getDuplicatedFieldFromSingleExtendingClass() {
		return sameField;
	}

	public int getDuplicatedInSuperAndBaseFromSuper() {
		return duplicateInSuperAndBase;
	}

	public int getDuplicatedInSuperSuperAndSuperFromSuper() {
		return duplicateInSuperSuperAndSuper;
	}

}
