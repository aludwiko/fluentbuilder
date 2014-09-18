/*
 * Created on 02-12-2012 17:52:55 by Andrzej Ludwikowski
 */
package info.ludwikowski.fluentbuilder.sample.withalltypes;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class ClassWithAllTypes {

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
	@SuppressWarnings("rawtypes")
	private List typeStringListWitoutGenerics;
	private Map<String, List<Integer>> complexGeneric;



	public Map<String, List<Integer>> getComplexGeneric() {
		return complexGeneric;
	}

	public void setComplexGeneric(Map<String, List<Integer>> complexGeneric) {
		this.complexGeneric = complexGeneric;
	}

	public byte getTypeByte() {
		return typeByte;
	}

	public void setTypeByte(byte typeByte) {
		this.typeByte = typeByte;
	}

	public Byte getTypeObjectByte() {
		return typeObjectByte;
	}

	public void setTypeObjectByte(Byte typeObjectByte) {
		this.typeObjectByte = typeObjectByte;
	}

	public short getTypeShort() {
		return typeShort;
	}

	public void setTypeShort(short typeShort) {
		this.typeShort = typeShort;
	}

	public Short getTypeObjectShort() {
		return typeObjectShort;
	}

	public void setTypeObjectShort(Short typeObjectShort) {
		this.typeObjectShort = typeObjectShort;
	}

	public int getTypeInt() {
		return typeInt;
	}

	public void setTypeInt(int typeInt) {
		this.typeInt = typeInt;
	}

	public Integer getTypeObjectInteger() {
		return typeObjectInteger;
	}

	public void setTypeObjectInteger(Integer typeObjectInteger) {
		this.typeObjectInteger = typeObjectInteger;
	}

	public long getTypeLong() {
		return typeLong;
	}

	public void setTypeLong(long typeLong) {
		this.typeLong = typeLong;
	}

	public Long getTypeObjectLong() {
		return typeObjectLong;
	}

	public void setTypeObjectLong(Long typeObjectLong) {
		this.typeObjectLong = typeObjectLong;
	}

	public double getTypeDouble() {
		return typeDouble;
	}

	public void setTypeDouble(double typeDouble) {
		this.typeDouble = typeDouble;
	}

	public Double getTypeObjectDouble() {
		return typeObjectDouble;
	}

	public void setTypeObjectDouble(Double typeObjectDouble) {
		this.typeObjectDouble = typeObjectDouble;
	}

	public float getTypeFloat() {
		return typeFloat;
	}

	public void setTypeFloat(float typeFloat) {
		this.typeFloat = typeFloat;
	}

	public Float getTypeObjectFloat() {
		return typeObjectFloat;
	}

	public void setTypeObjectFloat(Float typeObjectFloat) {
		this.typeObjectFloat = typeObjectFloat;
	}

	public char getTypeChar() {
		return typeChar;
	}

	public void setTypeChar(char typeChar) {
		this.typeChar = typeChar;
	}

	public Date getTypeDate() {
		return typeDate;
	}

	public void setTypeDate(Date typeDate) {
		this.typeDate = typeDate;
	}

	public boolean isTypeBoolean() {
		return typeBoolean;
	}

	public void setTypeBoolean(boolean typeBoolean) {
		this.typeBoolean = typeBoolean;
	}

	public Boolean getTypeObjectBoolean() {
		return typeObjectBoolean;
	}

	public void setTypeObjectBoolean(Boolean typeObjectBoolean) {
		this.typeObjectBoolean = typeObjectBoolean;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public List<String> getTypeStringList() {
		return typeStringList;
	}

	public void setTypeStringList(List<String> typeStringList) {
		this.typeStringList = typeStringList;
	}

	@SuppressWarnings("rawtypes")
	public List getTypeStringListWitoutGenerics() {
		return typeStringListWitoutGenerics;
	}

	@SuppressWarnings("rawtypes")
	public void setTypeStringListWitoutGenerics(List typeStringListWitoutGenerics) {
		this.typeStringListWitoutGenerics = typeStringListWitoutGenerics;
	}
}
