/*
 * Created on 31 sie 2014 21:44:19 by Andrzej
 */

package info.ludwikowski.fluentbuilder.generator;

import info.ludwikowski.fluentbuilder.sample.ClassWithAllTypes;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.io.annotation.FileContent;


public class AbstractBuilderGeneratorTest extends UnitilsJUnit4 {

	@FileContent("BuildersForClassWithAllTypes")
	private String buildersForClassWithAllTypes;
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private PrintStream printStream = new PrintStream(baos);


	@Test
	public void shouldGenerateAbstractBuilderAndBuilderForClassWithAllTypes() {

		// when
		AbstractBuilderGenerator.forClassWithWriter(ClassWithAllTypes.class, printStream).printBuilders();
		String result = baos.toString();

		// then
		LineByLineAssertions.assertThat(result).isEqualsLineByLine(buildersForClassWithAllTypes);
	}
}
