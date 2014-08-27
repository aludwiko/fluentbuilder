/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.Before;
import org.junit.Test;

import de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject;

import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;

/**
 * @author Jan van Esdonk
 */
public class PrinterForAbstractBuilderUnitTest {

    private PrinterForAbstractBuilder testPrinter = null;

    @Before
    public void setUp() {
        final ClassMirror classMirror = new ClassMirrorImpl(DoubleExtendsTestObject.class, new Context());
        testPrinter = new PrinterForAbstractBuilder(classMirror, new Context());
    }

    @Test
    public void shouldPrintBaseSetterForFieldWhichIsDuplicatedInSuperclasses() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(output, containsString("public abstract B withSameField(int sameField);"));
    }

    @Test
    public void shouldAddClassPrefixToDuplicatedSetterFromSuperclass() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(output, containsString("public abstract B withExtendsTestObjectSameField(int sameField)"));
    }

    @Test
    public void shouldAddClassPrefixToDuplicatedSetterFromSuperSuperclass() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(output, containsString("public abstract B withBaseTestObjectSameField(int sameField);"));
    }

    @Test
    public void shouldPrintInheritatedFieldSetter() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(output, containsString("public abstract B withUniqueExtendsField(int uniqueExtendsField);"));
    }

    @Test
    public void shouldPrintDoubleInheritatedFieldSetter() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(output, containsString("public abstract B withUniqueBaseField(int uniqueBaseField);"));
    }

    @Test
    public void shouldPrintAnnotatedFieldSetter() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(
                output,
                containsString("@ReferencedField(\"de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject.sameField\")"));
    }

    @Test
    public void shouldPrintAnnotatedFieldSettersWhenFieldNameAppearsInIndirectSubClassToo() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(
                output,
                containsString("@ReferencedField(\"de.bluecarat.fluentbuilder.samples.BaseTestObject.sameField\")"));
    }

    @Test
    public void shouldPrintAnnotatedFieldSettersWhenFieldNameAppearsInDirectSubClassToo() {
        testPrinter.printBuilderBody();
        final String output = testPrinter.getBufferAsString();
        assertThat(
                output,
                containsString("@ReferencedField(\"de.bluecarat.fluentbuilder.samples.ExtendsTestObject.sameField\")"));
    }

    @Test
    public void shouldPrintFieldClassAnnotationImport() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                containsString("import de.bluecarat.fluentbuilder.annotation.ReferencedField;"));
    }

    @Test
    public void shouldNotPrintSamePackageImports() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                not(containsString("import de.bluecarat.fluentbuilder.samples.ExtendsTestObject;")));
        assertThat(
                testPrinter.getBufferAsString(),
                not(containsString("import de.bluecarat.fluentbuilder.samples.BaseTestObject;")));
        assertThat(
                testPrinter.getBufferAsString(),
                not(containsString("import de.bluecarat.fluentbuilder.samples.DoubleExtendsTestObject;")));
    }

    @Test
    public void shouldPrintUnderscoreWhenNameConflictExists() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                containsString("public abstract B withBaseTestObjectNameConflict_(int NameConflict);"));
    }

    @Test
    public void shouldPrintSecondUnderscoreWhenDoubleNameConflictExists() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                containsString("public abstract B withBaseTestObjectBaseTestObjectNameConflict__(int BaseTestObjectNameConflict_);"));
    }

    @Test
    public void shouldPrintVarargsMethodWithSameNameAsSetterForCollections() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                containsString("public abstract B withListField(List<ExtendsTestObject> listField);"));
        assertThat(
                testPrinter.getBufferAsString(),
                containsString("public B withListField(ExtendsTestObject... listField)"));
    }

    @Test
    public void shouldImportGenericFromSubPackage() {
        testPrinter.printClass();
        assertThat(
                testPrinter.getBufferAsString(),
                containsString("import de.bluecarat.fluentbuilder.samples.sub.SubPackageClass;"));
    }

}
