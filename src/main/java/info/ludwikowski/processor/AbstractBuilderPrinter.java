/*
 * Created on 10-03-2013 11:16:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static info.ludwikowski.util.StringUtils.capitalize;
import static info.ludwikowski.util.TypeUtils.isList;
import static info.ludwikowski.util.TypeUtils.isSet;
import info.ludwikowski.generator.proxy.AbstractBuilder;
import info.ludwikowski.generator.proxy.AbstractBuilderFactory;
import info.ludwikowski.model.ClassMirror;
import info.ludwikowski.model.MemberMirror;

import java.io.Writer;
import java.util.Set;
import java.util.TreeSet;

public class AbstractBuilderPrinter extends ClassPrinter {

	private final ClassMirror classMirror;
	private final ProcessorContext processorContext;


	public AbstractBuilderPrinter(ProcessorContext processorContext, ClassMirror classMirror) {
		this.classMirror = classMirror;
		this.processorContext = processorContext;
	}
	
	private String abstractBuilderClassName(){
		return classMirror.getSimpleName() + processorContext.getBuilderClassPostfix();
	}
	

	@Override
	protected void printClassWithBody() {

		printBuilderBegin();
		printBuilderBody();
		printVarargsMethods();

		printBuilderEnd();
	}

	private void printBuilderBody() {

		increaseIndentation();
		println();

		for (MemberMirror memberMirror : classMirror.getMembers()) {

			String fieldName = memberMirror.getName();

			println("public abstract #0 #1#2(#3 #4);",
					abstractBuilderClassName(),
					processorContext.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getSimpleType(),
					fieldName);
		}

		decreaseIndentation();
	}
	
	@Override
	public String getFullClassName() {
		return getPackageName() + "." + abstractBuilderName();
	}

	private void printVarargsMethods() {
		increaseIndentation();
		println();

		for (MemberMirror memberMirror : classMirror.getVarArgsMembers()) {

			String fieldName = memberMirror.getName();

			println("public #0 #1#2(#3... #4){", abstractBuilderClassName(), processorContext.getMethodPrefix(), capitalize(fieldName), memberMirror.getCollectionElementSimpleName(), fieldName);
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
					processorContext.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getCollectionElementSimpleName(),
					memberMirror.getName());
		}
		else if (isSet(memberMirror.getCollectionType())) {
			println("return #0#1(new HashSet<#2>(Arrays.asList(#3));",
					processorContext.getMethodPrefix(),
					capitalize(fieldName),
					memberMirror.getCollectionElementSimpleName(),
					memberMirror.getName());
		}
	}

	private void printBuilderEnd() {
		println("}");
	}

	private void printBuilderBegin() {
		println("public abstract class #0<B> extends AbstractBuilder<#2, B> {", abstractBuilderName(), abstractBuilderClassName(), classMirror.getSimpleName());
	}

	private String abstractBuilderName() {
		return processorContext.getAbstractBuilderClassPrefix() + abstractBuilderClassName();
	}

	@Override
	protected Set<String> getFullClassNamesForImports() {

		Set<String> imports = new TreeSet<String>();

		imports.add(AbstractBuilder.class.getCanonicalName());
		imports.addAll(classMirror.getImports());

		return imports;
	}

	@Override
	protected String getPackageName() {
		return classMirror.getPackageName();
	}

	@Override
	protected void printClassComment() {
		println("/** ");
		println(" * Abstract builder for " + classMirror.getSimpleName());
		println(" * After changes in " + classMirror.getSimpleName() +
				" this class will be overriden, so dont' put any changes here, use "
				+ classMirror.getSimpleName() + processorContext.getBuilderClassPostfix() + " instead.");
		println(" */");
	}

}
