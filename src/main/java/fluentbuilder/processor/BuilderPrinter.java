/* 
 * Created on 10-03-2013 11:17:24 by Andrzej Ludwikowski
 */

package fluentbuilder.processor;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import fluentbuilder.generator.abstractproxy.AbstractBuilderFactory;
import fluentbuilder.model.ClassMirror;


public class BuilderPrinter extends ClassPrinter {

	private final ProcessorContext processorContext;
	private final ClassMirror classMirror;


	public BuilderPrinter(Writer writer, ProcessorContext processorContext, ClassMirror classMirror) {
		super(writer);
		this.processorContext = processorContext;
		this.classMirror = classMirror;
	}

	@Override
	public void printClassWithBody() {
		println("public #0#1 extends #2#0#1<#0#1> {",
				classMirror.getSimpleName(),
				processorContext.getBuilderClassPostfix(),
				processorContext.getAbstractBuilderClassPrefix());
		println("}");
	}

	@Override
	public List<String> getFullClassNamesForImports() {
		ArrayList<String> fullClassNames = new ArrayList<String>();
		fullClassNames.add(AbstractBuilderFactory.class.getCanonicalName());
		return fullClassNames;
	}

	@Override
	public String getPackageName() {
		return classMirror.getPackageName();
	}

}
