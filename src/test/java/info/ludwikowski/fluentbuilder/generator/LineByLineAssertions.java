/*
 * Created on 1 wrz 2014 21:49:37 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.generator;

import org.fest.assertions.api.AbstractAssert;

import com.google.common.base.Joiner;

/**
 * Custom assertion for testing generated builder/builders
 * 
 * @author Andrzej Ludwikowski
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
			assertThat(actualLine).overridingErrorMessage("line <%s> excepted:\n <%s> \n but was:\n <%s>", i, lines(expectedLines, i), lines(actualLines, i))
									.isEqualTo(expetedLine);
		}

		return this;
	}

	private String lines(String[] lines, int i) {
		if (i == 0 || i == lines.length - 1) {
			return lines[i];
		}
		return Joiner.on("\n").join(lines[i - 1], lines[i], lines[i + 1]);
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
