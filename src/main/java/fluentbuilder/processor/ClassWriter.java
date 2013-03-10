/* 
 * Created on 10-03-2013 11:11:55 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;

import fluentbuilder.model.ClassMirror;


public class ClassWriter {


	public void write(ProcessorContext context, Collection<ClassMirror> classMirrors) {

		OutputStream out = System.out;
		PrintStream printStream = new PrintStream(out);

	}

}
