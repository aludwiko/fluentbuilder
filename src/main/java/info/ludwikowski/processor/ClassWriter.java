/* 
 * Created on 10-03-2013 11:11:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import info.ludwikowski.model.ClassMirror;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.sun.mirror.apt.Filer.Location;



public class ClassWriter {

	private final ProcessorContext context;

	public ClassWriter(ProcessorContext context) {
		this.context = context;
	}

	public void write(Collection<ClassMirror> classMirrors) {
		
		Writer writer = new StringWriter();

		for (ClassMirror classMirror : classMirrors) {

			printAbstractBuilderPrinter(classMirror);
			printBuilderPrinter(classMirror);
			
//			new BuilderPrinter(writer, context, classMirror).printClass();
		}
		
		System.out.println(writer.toString());
		
//		try {
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void printBuilderPrinter(ClassMirror classMirror) {
		
		try {
			
			BuilderPrinter printer = new BuilderPrinter(context, classMirror);
			
			
			Filer filer = context.getProcessingEnvironment().getFiler();
			FileObject resource = filer.getResource(StandardLocation.SOURCE_OUTPUT, printer.getPackageName(), "TestBuilder.java");
			
			if (resource.getLastModified()< 0){
			
				FileObject fo = context.getProcessingEnvironment().getFiler()
						.createSourceFile(printer.getFullClassName());
	
				
				OutputStream os = fo.openOutputStream();
				PrintWriter pw = new PrintWriter(os);
	
				pw.print(printer.printClass());
	
				pw.flush();
				pw.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printAbstractBuilderPrinter(ClassMirror classMirror) {
		try {
			AbstractBuilderPrinter printer = new AbstractBuilderPrinter(context, classMirror);
			
			FileObject fo = context.getProcessingEnvironment().getFiler()
					.createSourceFile(printer.getFullClassName());

			OutputStream os = fo.openOutputStream();
			PrintWriter pw = new PrintWriter(os);

			pw.print(printer.printClass());

			pw.flush();
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
