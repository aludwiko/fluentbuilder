/*
 * Created on 10-03-2013 11:11:55 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import info.ludwikowski.fluentbuilder.common.BuilderPrinter;
import info.ludwikowski.fluentbuilder.common.PrinterForAbstractBuilder;
import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.ClassMirrorImpl;

/**
 * This class is responsible for writing out of ClassMirror generated builder
 * code to files.
 * @author Andrzej Ludwikowski
 */
public class ClassWriter {

    private static final String CHARSET = "UTF-8";
    private static final Logger LOGGER = Logger.getLogger(ClassMirrorImpl.class.getName());

    private final ProcessorContext context;

    /**
     * Default constructor for a ClassWriter.
     * @param context inhabits settings for the ClassWriter
     */
    public ClassWriter(final ProcessorContext context) {
        this.context = context;
    }

    /**
     * Writes a AbstractBuilder and Builder pair for a Collection of
     * ClassMirrors to .java files.
     * @param classMirrors Collection of ClassMirror for which Builders will be
     *            written to files.
     */
    public final void write(final Collection<ClassMirror> classMirrors) {

        for (final ClassMirror classMirror : classMirrors) {

            printAbstractBuilderPrinter(classMirror);
            printBuilderPrinter(classMirror);
        }
    }

    private void printBuilderPrinter(final ClassMirror classMirror) {

        try {

            final BuilderPrinter printer = new BuilderPrinter(classMirror, context);

            final Filer filer = context.getProcessingEnvironment().getFiler();
            final FileObject resource = filer.getResource(
                    StandardLocation.SOURCE_OUTPUT,
                    printer.getPackageName(),
                    printer.builderName() + ".java");
            if (resourceNotExists(resource)) {
                final FileObject fo = context.getProcessingEnvironment().getFiler()
                    .createSourceFile(printer.getFullClassName());
                final OutputStream outputStream = fo.openOutputStream();
                final OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, CHARSET);
                final PrintWriter printWriter = new PrintWriter(streamWriter);
                printWriter.print(printer.printClass());
                printWriter.flush();
                printWriter.close();
                context.logInfo("created: " + printer.getFullClassName());
                return;
            }
            context.logInfo("already exists: " + printer.getFullClassName());
        } catch (final IOException e) {
            context.logError(e.getMessage());
            LOGGER.severe("Could not create new class file for " + classMirror.getSimpleName());
        }
    }

    private boolean resourceNotExists(final FileObject resource) {
        return resource == null || resource.getLastModified() <= 0;
    }

    private void printAbstractBuilderPrinter(final ClassMirror classMirror) {
        try {
            final PrinterForAbstractBuilder printer = new PrinterForAbstractBuilder(classMirror, context);

            final FileObject fo = context.getProcessingEnvironment().getFiler()
                .createSourceFile(printer.getFullClassName());

            final OutputStream outputStream = fo.openOutputStream();
            final OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, CHARSET);
            final PrintWriter printWriter = new PrintWriter(streamWriter);

            printWriter.print(printer.printClass());

            printWriter.flush();
            printWriter.close();
            context.logInfo("updated or created class: " + printer.getFullClassName());

        } catch (final IOException e) {
            context.logError(e.getMessage());
            LOGGER.severe("Could not create new class file for " + classMirror.getSimpleName());
        }
    }

}
