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
public class DoubleInheritanceClass extends SingleInheritanceClass {

	private int duplicatedInSubClasses;
	private int duplicatedInTopAndDoubleInheritanceClass;
	private int duplicatedInSingleAndDoubleInheritanceClass;
	private int onlyInDoubleInheritanceClass;

}
