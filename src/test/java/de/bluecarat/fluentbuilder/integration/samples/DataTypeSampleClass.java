/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.integration.samples;

import java.util.Date;
import java.util.List;
import java.util.Map;

import de.bluecarat.fluentbuilder.annotation.GenerateBuilder;

/**
 * @author Jan van Esdonk
 */
@GenerateBuilder
@SuppressWarnings("unused")
public class DataTypeSampleClass {

    private byte typeByte;
    private Byte typeObjectByte;
    private short typeShort;
    private Short typeObjectShort;
    private int typeInt;
    private Integer typeObjectInteger;
    private long typeLong;
    private Long typeObjectLong;
    private double typeDouble;
    private Double typeObjectDouble;
    private float typeFloat;
    private Float typeObjectFloat;
    private char typeChar;
    private Date typeDate;
    private boolean typeBoolean;
    private Boolean typeObjectBoolean;
    private String typeString;
    private List<String> typeStringList;
    private Map<String, List<Integer>> complexGeneric;

    public byte getTypeByte() {
        return typeByte;
    }

    public Byte getTypeObjectByte() {
        return typeObjectByte;
    }

    public short getTypeShort() {
        return typeShort;
    }

    public Short getTypeObjectShort() {
        return typeObjectShort;
    }

    public int getTypeInt() {
        return typeInt;
    }

    public Integer getTypeObjectInteger() {
        return typeObjectInteger;
    }

    public long getTypeLong() {
        return typeLong;
    }

    public Long getTypeObjectLong() {
        return typeObjectLong;
    }

    public double getTypeDouble() {
        return typeDouble;
    }

    public Double getTypeObjectDouble() {
        return typeObjectDouble;
    }

    public float getTypeFloat() {
        return typeFloat;
    }

    public Float getTypeObjectFloat() {
        return typeObjectFloat;
    }

    public char getTypeChar() {
        return typeChar;
    }

    public Date getTypeDate() {
        return typeDate;
    }

    public boolean getTypeBoolean() {
        return typeBoolean;
    }

    public Boolean getTypeObjectBoolean() {
        return typeObjectBoolean;
    }

    public String getTypeString() {
        return typeString;
    }

    public List<String> getTypeStringList() {
        return typeStringList;
    }

    public Map<String, List<Integer>> getComplexGeneric() {
        return complexGeneric;
    }

}
