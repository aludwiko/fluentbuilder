/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.integration.samples;

import de.bluecarat.fluentbuilder.annotation.GenerateBuilder;

/**
 * @author Jan van Esdonk
 */
@GenerateBuilder
public class MultiConstructorSampleClass {

	final private boolean thirdCon;
	final private int constructorCounter;


	public MultiConstructorSampleClass() {
		this.constructorCounter = 1;
		this.thirdCon = false;
	}

	public MultiConstructorSampleClass(final int parameter1) {
		this.constructorCounter = 2;
		this.thirdCon = false;
	}

	public MultiConstructorSampleClass(final int parameter1, final boolean parameter2) {
		this.constructorCounter = 3;
		this.thirdCon = true;
	}

	public int getIndexOfConstructorUsedForInitialization() {
		return constructorCounter;
	}

	public boolean getThirdCon() {
		return thirdCon;
	}

}
