package info.ludwikowski.common;

import info.ludwikowski.model.ClassMirror;

import java.util.Set;

public class SingleBuilderPrinter extends AbstractBuilderPrinter {

	public SingleBuilderPrinter(ClassMirror classMirror, Context context) {
		super(classMirror, context);
	}

	@Override
	protected void printClassWithBody() {
		printBuilderBegin();
		printCreateMethod();
		printBuilderMethodPrefixGetter();
		printBuilderBuildMethod();
		printBuilderBody();
		printVarargsMethods();
		printBuilderEnd();
	}

	@Override
	protected String abstractBuilderReturnName() {
		return builderName();
	}

	@Override
	protected Set<String> getFullClassNamesForImports() {
		Set<String> imports = super.getFullClassNamesForImports();
		imports.add(AbstractBuilderFactory.class.getCanonicalName());
		return imports;
	}

	@Override
	protected void printClassComment() {
		println("/**");
		println(" * Abstract builder for #0.", classMirror.getSimpleName());
		println(" */");
	}

	@Override
	protected void printBuilderBegin() {
		println("public abstract class #0 extends AbstractBuilder<#1, #2> {", abstractBuilderReturnName(), classMirror.getSimpleName(), builderName());
	}

	@Override
	public String builderName() {
		return classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

}
