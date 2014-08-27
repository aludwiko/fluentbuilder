/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import java.util.List;

import de.bluecarat.fluentbuilder.samples.sub.SubPackageClass;

/**
 * @author Jan van Esdonk
 */
@SuppressWarnings("unused")
public class DoubleExtendsTestObject extends ExtendsTestObject {

    private int sameField;
    private int uniqueDoubleExtendsField;
    private int duplicateInSuperSuperAndBase;
    private int duplicateInSuperAndBase;
    private BaseTestObject samePackageField;
    private int randomNameField;
    private List<String> duplicatedCollection;
    private List<SubPackageClass> genericField;
    private List<ExtendsTestObject> listField;

    public DoubleExtendsTestObject(int intField, String stringField, char charField, int sameField, int uniqueBase,
                                   int sameField2, int uniqueExtendsField, int sameField3) {
        super(intField, stringField, charField, sameField, uniqueBase, sameField2, uniqueExtendsField);
        sameField = sameField3;
    }

    public DoubleExtendsTestObject(int firstParameter, int secondParameter) {
        super(firstParameter, "constructedWith executed", 'b', secondParameter, 11, 11, 11);
    }

    public int getDuplicatedFieldFromDoubleExtendingClass() {
        return sameField;
    }

    public int getDuplicatedInSuperAndBaseFromBase() {
        return duplicateInSuperAndBase;
    }

    public int getDuplicatedInSuperSuperAndBaseFromBase() {
        return duplicateInSuperSuperAndBase;
    }

    public int getRandomNameField() {
        return randomNameField;
    }

}
