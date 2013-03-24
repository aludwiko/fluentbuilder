/* 
 * Created on 10-03-2013 11:16:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static info.ludwikowski.util.StringUtils.capitalize;
import info.ludwikowski.generator.proxy.AbstractBuilderFactory;
import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.model.MemberMirror;

import java.io.Writer;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class AbstractBuilderPrinter extends ClassPrinter {

	private static final String PACKAGE_REGEXP = "([a-z]*\\.)";

	private final ClassMirror classMirror;
	private final ProcessorContext processorContext;


	public AbstractBuilderPrinter(Writer writer, ProcessorContext processorContext, ClassMirror classMirror) {
		super(writer);
		this.classMirror = classMirror;
		this.processorContext = processorContext;
	}

	@Override
	public void printClassWithBody() {
		
		printBuilderBegin();
		if (processorContext.isStaticCreate()) {
			printCreateMethod();
		}
		printBuilderBody();
		printVarargsMethods();

		printBuilderEnd();
	}

	public void printBuilderBody() {

		increaseIndentation();
		println();

		for (MemberMirror memberMirror : classMirror.getMembers()) {

			String fieldName = memberMirror.getName();

			println("public abstract #0 #1#2(#3 #4);",
					builderName(),
					processorContext.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getType(),
					fieldName);
		}

		decreaseIndentation();
	}

	public void printVarargsMethods() {
		increaseIndentation();
		println();

		for (MemberMirror memberMirror : classMirror.getVarArgsMembers()) {

			String fieldName = memberMirror.getName();

			println("public #0 #1#2(#3... #4){", builderName(), processorContext.getMethodPrefix(), capitalize(fieldName), memberMirror.getType(), fieldName);
			increaseIndentation();
			printCollectionCreation(memberMirror);
			decreaseIndentation();
			println("}");
		}
		decreaseIndentation();
	}

	private void printCollectionCreation(MemberMirror memberMirror) {

		String type = removePackage(memberMirror.getType());
		String fieldName = memberMirror.getName();

		if (List.class.getCanonicalName().equals(type)) {
			println("return #0#1(Lists.newArrayList(#2));", processorContext.getMethodPrefix(), capitalize(fieldName), fieldName);
		}
		else if (Set.class.getCanonicalName().equals(type)) {
			println("return #0#1(Sets.newHashSet(#2));", processorContext.getMethodPrefix(), capitalize(fieldName), fieldName);
		}
	}

	private static String removePackage(String value) {
		return value.replaceAll(PACKAGE_REGEXP, "");
	}

	public void printCreateMethod() {
		increaseIndentation();
		println();
		println("public static #0 #1(){", builderName(), processorContext.getStaticCreateMethodName());
		increaseIndentation();
		println("return AbstractBuilderFactory.createImplementation(#0.class);", builderName());
		decreaseIndentation();
		println("}");
		decreaseIndentation();
	}

	private void printBuilderEnd() {
		println("}");
	}

	public void printBuilderBegin() {
		println("public abstract class #0<B> extends #1<#2, B> {", abstractBuilderName(), builderName(), classMirror.getSimpleName());
	}

	private String abstractBuilderName() {
		return processorContext.getAbstractBuilderClassPrefix() + builderName();
	}

	private String builderName() {
		return classMirror.getSimpleName() + processorContext.getBuilderClassPostfix();
	}

	public void printComment(String className) {
		println("/** ");
		println(" * Fluent builder for " + className);
		println(" * ");
		println(" */");
	}

	@Override
	public Set<String> getFullClassNamesForImports() {

		Set<String> imports = new TreeSet<String>();

		imports.add(AbstractBuilderFactory.class.getCanonicalName());
		imports.addAll(classMirror.getImports());

		return imports;
	}

	@Override
	public String getPackageName() {
		return classMirror.getPackageName();
	}

	@Override
	public void printClassComment() {
		println("/** ");
		println(" * Abstract builder for " + classMirror.getSimpleName());
		println(" * After changes in " + classMirror.getSimpleName() +
				" this class will be overriden, so dont' put any changes here, use "
				+ classMirror.getSimpleName() + processorContext.getBuilderClassPostfix() + " instead.");
		println(" */");
	}

}
