/* 
 * Created on 10-03-2013 11:17:24 by Andrzej Ludwikowski
 */

package info.ludwikowski.common;

import info.ludwikowski.model.ClassMirror;

import java.util.Set;
import java.util.TreeSet;



public class BuilderPrinter extends ClassPrinter {

	
	public BuilderPrinter(ClassMirror classMirror, Context context) {
		super(classMirror, context);
	}

	@Override
	public String getFullClassName() {
		return getPackageName() + "." + classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

	@Override
	protected void printClassWithBody() {
		
		
		println("public abstract class #0 extends #1#0<#0> {",
				builderName(),
				context.getAbstractBuilderClassPrefix());
		
		printCreateMethod();
		println("}");
	}

	@Override
	protected Set<String> getFullClassNamesForImports() {
		Set<String> fullClassNames = new TreeSet<String>();
		if (context.isStaticCreate()) {
			fullClassNames.add(AbstractBuilderFactory.class.getCanonicalName());
		}
		return fullClassNames;
	}

	@Override
	public String getPackageName() {
		return classMirror.getPackageName();
	}

	@Override
	protected void printClassComment() {
		println("/** ");
		println(" * Fluent builder for #0.", classMirror.getSimpleName());
		println(" * Don't hesitate to put your custom methods here. ");
		println(" */");
	}

	@Override
	public String builderName() {
		return classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

}
