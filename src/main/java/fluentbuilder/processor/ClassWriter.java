/* 
 * Created on 10-03-2013 11:11:55 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

import fluentbuilder.model.ClassMirror;


public class ClassWriter {

	private final ProcessorContext context;

	public ClassWriter(ProcessorContext context) {
		this.context = context;
	}

	public void write(Collection<ClassMirror> classMirrors) {

		Writer writer = new StringWriter();

		for (ClassMirror classMirror : classMirrors) {


			new AbstractBuilderPrinter(writer).printClass();
		}

		System.out.println(writer.toString());

	}

}
