/*
 * Created on 22-03-2013 17:48:01 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.model.MemberMirror;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class AbstractBuilderPrinterTest {

	private ProcessingEnvironment processingEnv = mock(ProcessingEnvironment.class);
	private ProcessorContext processorContext = new ProcessorContext(processingEnv);
	private Writer writer = new StringWriter();


	@Test
	public void shouldWriteToSysout() {

		// given
		ClassMirror mirror = mock(ClassMirror.class);
		AbstractBuilderPrinter abstractBuilderPrinter = new AbstractBuilderPrinter(writer, processorContext, mirror);

		String name = "Order";
		String packageName = "test.builder";
		given(mirror.getSimpleName()).willReturn(name);
		given(mirror.getPackageName()).willReturn(packageName);
		given(mirror.getImports()).willReturn(someImports());
		given(mirror.getMembers()).willReturn(members());
		given(mirror.getVarArgsMembers()).willReturn(varArgsMembers());

		// when
		abstractBuilderPrinter.printClass();

		// then
		System.out.println(writer.toString());
	}

	private List<MemberMirror> varArgsMembers() {

		List<MemberMirror> members = Lists.newArrayList();

		// members.add(new MemberMirrorImpl("foos", "Set", "Set<Foo>", null));
		// members.add(new MemberMirrorImpl("bars", "List", "List<Bar>", null));

		return members;
	}

	private List<MemberMirror> members() {

		List<MemberMirror> members = Lists.newArrayList();

		// members.add(new MemberMirrorImpl("name", "String", "String", null));

		return members;
	}

	private Set<String> someImports() {

		Set<String> imports = Sets.newTreeSet();

		imports.add("java.io.StringWriter");
		imports.add("java.io.Writer");
		imports.add("javax.annotation.processing.ProcessingEnvironment");
		imports.add("info.ludwikowski.model.ClassMirror");

		return imports;
	}
}
