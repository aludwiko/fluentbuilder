/* 
 * Created on 10-03-2013 16:18:19 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;

import javax.annotation.processing.ProcessingEnvironment;

import org.junit.Test;

import de.bluecarat.fluentbuilder.integration.samples.DataTypeSampleClass;

import info.ludwikowski.fluentbuilder.common.BuilderPrinter;
import info.ludwikowski.fluentbuilder.common.Context;
import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;

/**
 * @author Andrzej Ludwikowski
 */
public class BuilderPrinterTest {

    private ProcessingEnvironment processingEnv = mock(ProcessingEnvironment.class);
    private ProcessorContext processorContext = new ProcessorContext(processingEnv);

    @Test
    public void shouldPrintBuilderFromClass() {

        // given
        ClassMirror mirror = new ClassMirrorImpl(DataTypeSampleClass.class, new Context());
        BuilderPrinter builderPrinter = new BuilderPrinter(mirror, processorContext);

        // when
        builderPrinter.printClass();

        // then
        assertThat(
                builderPrinter.getBufferAsString(),
                containsString("public abstract class DataTypeSampleClassBuilder extends AbstractDataTypeSampleClassBuilder<DataTypeSampleClassBuilder>"));
    }
}
