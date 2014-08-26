/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.Before;
import org.junit.Test;

import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;
import info.ludwikowski.fluentbuilder.model.ImportsFactory;

/**
 * @author Jan van Esdonk
 */
public class BuilderPrinterUnitTest {

    private AbstractClassPrinter testPrinter = null;

    @Before
    public void setUp() {
        ClassMirrorImpl dummyClassMirror = new ClassMirrorImpl(ImportsFactory.class, new Context());
        testPrinter = new BuilderPrinter(dummyClassMirror, new Context());
        testPrinter.decreaseIndentation();
    }

    @Test
    public void shouldReplaceAllHashNumbers() {
        final String exampleString = "Text with #0, #1 or maybe #2 replacements";
        final String expectedString = "Text with zero, one or maybe two replacements";
        testPrinter.printLine(exampleString, "zero", "one", "two");
        testPrinter.getClassMirror().getPackageName();
        final String replacedText = testPrinter.printClass();
        assertThat(replacedText, containsString(expectedString));
    }

    @Test
    public void shouldNotPrintFieldClassAnnotationImport() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                not(containsString("import info.ludwikowski.annotation.FullFieldName;")));
    }
}
