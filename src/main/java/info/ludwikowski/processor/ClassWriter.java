/* 
 * Created on 10-03-2013 11:11:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import info.ludwikowski.common.AbstractBuilderPrinter;
import info.ludwikowski.common.BuilderPrinter;
import info.ludwikowski.model.ClassMirror;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;



public class ClassWriter {

	private final ProcessorContext context;

	public ClassWriter(ProcessorContext context) {
		this.context = context;
	}

	public void write(Collection<ClassMirror> classMirrors) {
		
		for (ClassMirror classMirror : classMirrors) {

			printAbstractBuilderPrinter(classMirror);
			printBuilderPrinter(classMirror);
		}
	}

	private void printBuilderPrinter(ClassMirror classMirror) {
		
		try {

			BuilderPrinter printer = new BuilderPrinter(classMirror, context);

			Filer filer = context.getProcessingEnvironment().getFiler();
			FileObject resource = filer.getResource(StandardLocation.SOURCE_OUTPUT,
					printer.getPackageName(),
					printer.builderName() + ".java");

			if (resourceNotExists(resource)) {

				FileObject fo = context.getProcessingEnvironment().getFiler()
										.createSourceFile(printer.getFullClassName());

				OutputStream os = fo.openOutputStream();
				PrintWriter pw = new PrintWriter(os);

				pw.print(printer.printClass());

				pw.flush();
				pw.close();
				context.logInfo("created: " + printer.getFullClassName());
				return;
			}
			context.logInfo("already exists: " + printer.getFullClassName());
		}
		catch (IOException e) {
			context.logError(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean resourceNotExists(FileObject resource) {
		return resource == null || resource.getLastModified() <= 0;
	}

	private void printAbstractBuilderPrinter(ClassMirror classMirror) {
		try {
			AbstractBuilderPrinter printer = new AbstractBuilderPrinter(classMirror, context);
			
			FileObject fo = context.getProcessingEnvironment().getFiler()
					.createSourceFile(printer.getFullClassName());

			OutputStream os = fo.openOutputStream();
			PrintWriter pw = new PrintWriter(os);

			pw.print(printer.printClass());

			pw.flush();
			pw.close();
			context.logInfo("updataed or created class: " + printer.getFullClassName());

		} catch (IOException e) {
			context.logError(e.getMessage());
			e.printStackTrace();
		}
	}

}
