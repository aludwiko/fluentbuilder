/*
 * Created on 10-03-2013 11:16:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.common;

import static info.ludwikowski.common.AbstractBuilder.BUILDER_METHOD_PREFIX;
import static info.ludwikowski.util.StringUtils.capitalize;
import static info.ludwikowski.util.TypeUtils.isList;
import static info.ludwikowski.util.TypeUtils.isSet;
import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.model.MemberMirror;

import java.util.Set;
import java.util.TreeSet;

public class AbstractBuilderPrinter extends ClassPrinter {

	private static final String GENERIC_B = "B";


	public AbstractBuilderPrinter(ClassMirror classMirror, Context context) {
		super(classMirror, context);
	}

	private String abstractBuilderClassName(){
		return classMirror.getSimpleName() + context.getBuilderClassPostfix();
	}

	@Override
	protected void printClassWithBody() {

		printBuilderBegin();
		printBuilderMethodPrefixGetter();
		printBuilderBuildMethod();
		printBuilderBody();
		printVarargsMethods();
		printBuilderEnd();
	}

	protected void printBuilderBuildMethod() {
		if (context.getBuildMethodName() != null) {
			println();
			increaseIndentation();
			println("public #0 #1(){", classMirror.getSimpleName(), context.getBuildMethodName());
			increaseIndentation();
			println("return build();");
			decreaseIndentation();
			println("}");
			decreaseIndentation();
		}
	}

	/**
	 * public static final String getPrefix() {
	 * return "with";
	 * }
	 */
	protected void printBuilderMethodPrefixGetter() {
		if (!BUILDER_METHOD_PREFIX.equals(context.getMethodPrefix())) {
			println();
			increaseIndentation();
			println("public static final String getPrefix() {");
			increaseIndentation();
			println("return \"#0\";", context.getMethodPrefix());
			decreaseIndentation();
			println("}");
			decreaseIndentation();
		}
	}

	protected void printBuilderBody() {

		increaseIndentation();
		println();

		for (MemberMirror memberMirror : classMirror.getMembers()) {

			String fieldName = memberMirror.getName();

			println("public abstract #0 #1#2(#3 #4);",
					abstractBuilderReturnName(),
					context.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getSimpleType(),
					fieldName);
		}

		decreaseIndentation();
	}
	
	@Override
	public String getFullClassName() {
		return getPackageName() + "." + builderName();
	}

	protected void printVarargsMethods() {

		if (!context.isVarargsForCollections()) {
			return;
		}

		increaseIndentation();
		println();

		for (MemberMirror memberMirror : classMirror.getVarArgsMembers()) {

			String fieldName = memberMirror.getName();

			println("public #0 #1#2(#3... #4){",
					abstractBuilderReturnName(),
					context.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getCollectionElementSimpleName(),
					fieldName);
			increaseIndentation();
			printCollectionCreation(memberMirror);
			decreaseIndentation();
			println("}");
		}
		decreaseIndentation();
	}

	private void printCollectionCreation(MemberMirror memberMirror) {

		String fieldName = memberMirror.getName();

		if (isList(memberMirror.getCollectionType())) {
			println("return #0#1(new ArrayList<#2>(Arrays.asList(#3)));",
					context.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getCollectionElementSimpleName(),
					memberMirror.getName());
		}
		else if (isSet(memberMirror.getCollectionType())) {
			println("return #0#1(new HashSet<#2>(Arrays.asList(#3)));",
					context.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getCollectionElementSimpleName(),
					memberMirror.getName());
		}
	}

	protected void printBuilderEnd() {
		println("}");
	}

	protected void printBuilderBegin() {
		println("public abstract class #0 extends AbstractBuilder<#1, #2> {", abstractBuilderFullClassName(), classMirror.getSimpleName(), GENERIC_B);
	}
	
	private String abstractBuilderFullClassName() {
		return context.getAbstractBuilderClassPrefix() + abstractBuilderClassName() + "<B>";
	}

	protected String abstractBuilderReturnName() {
		return GENERIC_B;
	}

	@Override
	public String builderName() {
		return context.getAbstractBuilderClassPrefix() + abstractBuilderClassName();
	}

	@Override
	protected Set<String> getFullClassNamesForImports() {

		Set<String> imports = new TreeSet<String>();

		imports.add(AbstractBuilder.class.getCanonicalName());
		imports.addAll(classMirror.getImports());

		return imports;
	}

	@Override
	public String getPackageName() {
		return classMirror.getPackageName();
	}

	@Override
	protected void printClassComment() {
		println("/** ");
		println(" * Abstract builder for #0. ", classMirror.getSimpleName());
		println(" * After changes in #0 this class will be overridden, so don't put any changes here, use #1 instead.", classMirror.getSimpleName(), abstractBuilderClassName());
		println(" */");
	}

}
