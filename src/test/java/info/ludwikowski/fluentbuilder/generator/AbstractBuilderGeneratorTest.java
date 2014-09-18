/*
 * Created on 31 sie 2014 21:44:19 by Andrzej
 */

package info.ludwikowski.fluentbuilder.generator;

import info.ludwikowski.fluentbuilder.sample.inheritance.Child;
import info.ludwikowski.fluentbuilder.sample.withalltypes.ClassWithAllTypes;
import info.ludwikowski.fluentbuilder.sample.withdefaultcontructor.ClassWithDefaultConstructor;
import info.ludwikowski.fluentbuilder.sample.withoutdefaultconstructor.ClassWithoutDefaultConstructor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.io.annotation.FileContent;


public class AbstractBuilderGeneratorTest extends UnitilsJUnit4 {

	@FileContent("BuildersForClassWithAllTypes")
	private String buildersForClassWithAllTypes;
	@FileContent("BuildersForClassWithoutDefaultConstructor")
	private String buildersForClassWithoutDefaultConstrutor;
	@FileContent("BuildersForClassWithDefaultConstructor")
	private String buildersForClassWithDefaultConstrutor;
	@FileContent("BuildersForClassWithInheritatedFields")
	private String buildersForClassWithInheritatedFields;
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

	@Test
	public void shouldGenerateAbstractBuilderAndBuilderForClassWithoutDefaultConstrutor() {

		// when
		AbstractBuilderGenerator.forClassWithWriter(ClassWithoutDefaultConstructor.class, printStream).printBuilders();
		String result = baos.toString();

		// then
		LineByLineAssertions.assertThat(result).isEqualsLineByLine(buildersForClassWithoutDefaultConstrutor);
	}

	@Test
	public void shouldGenerateAbstractBuilderAndBuilderForClassWithDefaultConstrutor() {

		// when
		AbstractBuilderGenerator.forClassWithWriter(ClassWithDefaultConstructor.class, printStream).printBuilders();
		String result = baos.toString();

		// then
		LineByLineAssertions.assertThat(result).isEqualsLineByLine(buildersForClassWithDefaultConstrutor);
	}

	@Test
	public void shouldGenerateAbstractBuilderAndBuilderForClassWithInheritatedFields() {

		// when
		AbstractBuilderGenerator.forClassWithWriter(Child.class, printStream).printBuilders();
		String result = baos.toString();
		System.out.println(result);

		// then
		LineByLineAssertions.assertThat(result).isEqualsLineByLine(buildersForClassWithInheritatedFields);
	}
}
