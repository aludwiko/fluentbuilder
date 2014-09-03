/*
 * Created on 1 wrz 2014 21:49:37 by Andrzej
 */

package info.ludwikowski.fluentbuilder.generator;

import org.fest.assertions.api.AbstractAssert;

/**
 * Custom assertion for testing generated builder/builders
 * 
 * @author Andrzej
 * 
 */
public class LineByLineAssertions extends AbstractAssert<LineByLineAssertions, String> {

	protected LineByLineAssertions(String actual) {
		super(actual, LineByLineAssertions.class);
	}

	public static LineByLineAssertions assertThat(String actual) {
		return new LineByLineAssertions(actual);
	}

	public LineByLineAssertions isEqualsLineByLine(String expected) {
		String[] expectedLines = splitLines(expected);
		String[] actualLines = splitLines(actual);

		for (int i = 0; i < expectedLines.length; i++) {
			String expetedLine = expectedLines[i];
			String actualLine = actualLines[i];
			assertThat(actualLine).isEqualTo(expetedLine);
		}

		return this;
	}

	private String[] splitLines(String expected) {
		if (expected.contains("\r\n")) {
			return expected.split("\r\n");
		}
		else {
			return expected.split("\n");
		}
	}

}
