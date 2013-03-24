/* 
 * Created on 10-03-2013 11:11:55 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import info.ludwikowski.model.ClassMirror;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;



public class ClassWriter {

	private final ProcessorContext context;

	public ClassWriter(ProcessorContext context) {
		this.context = context;
	}

	public void write(Collection<ClassMirror> classMirrors) {

		Writer writer = new StringWriter();

		for (ClassMirror classMirror : classMirrors) {

			new AbstractBuilderPrinter(writer, classMirror).printClass();
		}

		System.out.println(writer.toString());

	}

}
