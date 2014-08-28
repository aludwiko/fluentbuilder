/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.integration.samples;

import de.bluecarat.fluentbuilder.annotation.GenerateBuilder;

/**
 * @author Jan van Esdonk
 */
@GenerateBuilder
@SuppressWarnings("unused")
public class TopSampleClass {

	private int duplicatedInSubClasses;
	private int duplicatedInTopAndSingleInheritanceClass;
	private int duplicatedInTopAndDoubleInheritanceClass;
	private int onlyInTopSampleClass;


	public int getOnlyInTopSampleClass() {
		return onlyInTopSampleClass;
	}

	public int getTopSampleClassDuplicatedInTopAndSingleInheritanceClass() {
		return duplicatedInTopAndSingleInheritanceClass;
	}

	public int getTopSampleClassDuplicatedInTopAndDoubleInheritanceClass() {
		return duplicatedInTopAndDoubleInheritanceClass;
	}

}
