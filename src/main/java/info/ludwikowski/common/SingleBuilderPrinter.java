package info.ludwikowski.common;

import info.ludwikowski.model.ClassMirror;

import java.util.Set;

public class SingleBuilderPrinter extends AbstractBuilderPrinter {

	public SingleBuilderPrinter(Context context, ClassMirror classMirror) {
		super(context, classMirror);
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
	protected String abstractBuilderFullName() {
		return builderName();
	}

	@Override
	protected Set<String> getFullClassNamesForImports() {
		Set<String> imports = super.getFullClassNamesForImports();
		imports.add(AbstractBuilderFactory.class.getCanonicalName());
		return imports;
	}

	private void printCreateMethod() {

		if (!context.isStaticCreate()) {
			return;
		}

		increaseIndentation();
		println();
		println("public static #0 #1(){", builderName(), context.getStaticCreateMethodName());
		increaseIndentation();
		println("return AbstractBuilderFactory.createImplementation(#0.class);", builderName());
		decreaseIndentation();
		println("}");
		decreaseIndentation();
	}

	@Override
	protected void printClassComment() {
		println("/**");
		println(" * Abstract builder for #0.", classMirror.getSimpleName());
		println(" */");
	}

	@Override
	protected void printBuilderBegin() {
		println("public abstract class #0 extends AbstractBuilder<#1, #2> {", abstractBuilderFullName(), builderName(), classMirror.getSimpleName());
	}

	public String builderName() {
		return classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

}
