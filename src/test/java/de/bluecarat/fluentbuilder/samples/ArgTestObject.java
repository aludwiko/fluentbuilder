/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import java.util.Date;

/**
 * @author Jan van Esdonk
 */
public class ArgTestObject {

    private final int intField;
    private final boolean booleanField;
    private final short shortField;
    private final long longField;
    private final byte byteField;
    private final float floatField;
    private final char charField;
    private final double doubleField;
    private final Date objectField;
    private final boolean createdByShortest;
    private int inheritatedField;
    private int doubleInheritatedField;
    private int sameField;

    /**
     * Shortest constructor using all types of fields fields
     * @param intField
     * @param booleanField
     * @param shortField
     * @param longField
     * @param byteField
     * @param floatField
     * @param charField
     * @param doubleField
     * @param objectField
     */
    public ArgTestObject(int intField, boolean booleanField, short shortField, long longField, byte byteField,
                         float floatField, char charField, double doubleField, Date objectField) {
        this.intField = intField;
        this.booleanField = booleanField;
        this.shortField = shortField;
        this.longField = longField;
        this.byteField = byteField;
        this.floatField = floatField;
        this.charField = charField;
        this.doubleField = doubleField;
        this.objectField = objectField;
        this.createdByShortest = true;
    }

    public int getIntField() {
        return intField;
    }

    public boolean isBooleanField() {
        return booleanField;
    }

    public short getShortField() {
        return shortField;
    }

    public long getLongField() {
        return longField;
    }

    public byte getByteField() {
        return byteField;
    }

    public float getFloatField() {
        return floatField;
    }

    public char getCharField() {
        return charField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public Date getObjectField() {
        return objectField;
    }

    public boolean isCreatedByShortest() {
        return createdByShortest;
    }

    /**
     * Longer constructor using all types of fields plus one
     * @param intField
     * @param booleanField
     * @param shortField
     * @param longField
     * @param byteField
     * @param floatField
     * @param charField
     * @param doubleField
     * @param objectField
     * @param dummy
     */
    public ArgTestObject(int intField, boolean booleanField, short shortField, long longField, byte byteField,
                         float floatField, char charField, double doubleField, Date objectField, boolean dummy) {
        this.intField = intField;
        this.booleanField = booleanField;
        this.shortField = shortField;
        this.longField = longField;
        this.byteField = byteField;
        this.floatField = floatField;
        this.charField = charField;
        this.doubleField = doubleField;
        this.objectField = objectField;
        this.createdByShortest = false;
        this.sameField = 0;
        this.inheritatedField = 0;
    }

    public int getHiddenField() {
        return sameField;
    }

    public int getInheritatedField() {
        return inheritatedField;
    }

    public int getDoubleInheritatedField() {
        return doubleInheritatedField;
    }

    public void setDoubleInheritatedField(int doubleInheritatedField) {
        this.doubleInheritatedField = doubleInheritatedField;
    }

}
