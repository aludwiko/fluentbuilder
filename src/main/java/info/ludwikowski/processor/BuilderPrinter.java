/* 
 * Created on 10-03-2013 11:17:24 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import info.ludwikowski.generator.proxy.AbstractBuilderFactory;
import info.ludwikowski.model.ClassMirror;

import java.io.Writer;
import java.util.Set;
import java.util.TreeSet;



public class BuilderPrinter extends ClassPrinter {

	private final ProcessorContext processorContext;
	private final ClassMirror classMirror;


	public BuilderPrinter(ProcessorContext processorContext, ClassMirror classMirror) {
		this.processorContext = processorContext;
		this.classMirror = classMirror;
	}
	
	@Override
	public String getFullClassName() {
		return getPackageName() + "." + classMirror.getSimpleName() + processorContext.getBuilderClassPostfix() ;
	}

	@Override
	protected void printClassWithBody() {
		
		
		println("public abstract class #0#1 extends #2#0#1<#0#1> {",
				classMirror.getSimpleName(),
				processorContext.getBuilderClassPostfix(),
				processorContext.getAbstractBuilderClassPrefix());
		
		if (processorContext.isStaticCreate()) {
			printCreateMethod();
		}
		
		println("}");
	}
	
	private void printCreateMethod() {
		increaseIndentation();
		println();
		println("public static #0 #1(){", builderName(), processorContext.getStaticCreateMethodName());
		increaseIndentation();
		println("return AbstractBuilderFactory.createImplementation(#0.class);", builderName());
		decreaseIndentation();
		println("}");
		decreaseIndentation();
	}
	
	private String builderName() {
		return classMirror.getSimpleName() + processorContext.getBuilderClassPostfix();
	}
	
	@Override
	protected Set<String> getFullClassNamesForImports() {
		Set<String> fullClassNames = new TreeSet<String>();
		fullClassNames.add(AbstractBuilderFactory.class.getCanonicalName());
		return fullClassNames;
	}

	@Override
	protected String getPackageName() {
		return classMirror.getPackageName();
	}

	@Override
	protected void printClassComment() {
		println("/** ");
		println(" * Fluent builder for " + classMirror.getSimpleName());
		println(" * Don't hasitate to put your custom methods here. ");
		println(" */");
	}

}
