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
public class SingleInheritanceClass extends TopSampleClass {

	private int duplicatedInSubClasses;
	private int duplicatedInTopAndSingleInheritanceClass;
	private int duplicatedInSingleAndDoubleInheritanceClass;
	private int onlyInSingleInheritanceClass;


	public int getOnlyInSingleInheritanceClass() {
		return onlyInSingleInheritanceClass;
	}

	public int getSingleInheritanceClassDuplicatedInSingleAndDoubleInheritanceClass() {
		return duplicatedInSingleAndDoubleInheritanceClass;
	}

}
