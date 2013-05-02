/* 
 * Created on 10-03-2013 16:18:19 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.processor.BuilderPrinter;
import info.ludwikowski.processor.ProcessorContext;

import java.io.StringWriter;
import java.io.Writer;

import javax.annotation.processing.ProcessingEnvironment;

import org.junit.Test;



public class BuilderPrinterTest {

	private ProcessingEnvironment processingEnv = mock(ProcessingEnvironment.class);
	private ProcessorContext processorContext = new ProcessorContext(processingEnv);
	private Writer writer = new StringWriter();



	@Test
	public void should() {

		// given
		ClassMirror mirror = mock(ClassMirror.class);
		BuilderPrinter builderPrinter = new BuilderPrinter(processorContext, mirror);
		
		String name = "Order";
		String packageName = "test.builder";
		given(mirror.getSimpleName()).willReturn(name);
		given(mirror.getPackageName()).willReturn(packageName);

		// when
		builderPrinter.printClass();

		// then
		System.out.println(writer.toString());
	}
}
