/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

/**
 * @author Jan van Esdonk
 */
public class BaseTestObject {
    private int intField;
    private String stringField;
    private char charField;
    private int sameField;
    private int uniqueBaseField;
    private int duplicateInSuperSuperAndBase;
    private int duplicateInSuperSuperAndSuper;
    private int BaseTestObjectNameConflict;
    private int NameConflict;
    private int BaseTestObjectNameConflict_;

    public BaseTestObject(int intField, String stringField, char charField, int sameField, int uniqueBaseField) {
        super();
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

    public int getDuplicatedInSuperSuperAndBaseFromSuperSuper() {
        return duplicateInSuperSuperAndBase;
    }

    public int getDuplicatedInSuperSuperAndSuperFromSuperSuper() {
        return duplicateInSuperSuperAndSuper;
    }

    public int getBaseTestObjectNameConflictFromSuper() {
        return BaseTestObjectNameConflict;
    }

    public char getCharField() {
        return charField;
    }

    public void setCharField(char charField) {
        this.charField = charField;
    }

    public int getNameConflict() {
        return NameConflict;
    }

    public void setNameConflict(int nameConflict) {
        NameConflict = nameConflict;
    }

    public int getBaseTestObjectNameConflict_() {
        return BaseTestObjectNameConflict_;
    }

    public void setBaseTestObjectNameConflict_(int baseTestObjectNameConflict_) {
        BaseTestObjectNameConflict_ = baseTestObjectNameConflict_;
    }

}
