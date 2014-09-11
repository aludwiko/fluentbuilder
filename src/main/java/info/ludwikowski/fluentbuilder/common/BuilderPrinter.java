/*
 * Created on 10-03-2013 11:17:24 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */

package info.ludwikowski.fluentbuilder.common;

import info.ludwikowski.fluentbuilder.model.ClassMirror;

import java.util.Set;
import java.util.TreeSet;

/**
 * The BuilderPrinter class can print out generated (non-abstract-)Builders.
 * 
 * @author Andrzej Ludwikowski
 */
public class BuilderPrinter extends AbstractClassPrinter {

	/**
	 * Creates a BuilderPrinter.
	 * 
	 * @param classMirror - ClassMirror instance of the class which is built by
	 *            the printed builder
	 * @param context - Context instance which defines the parameters for the
	 *            printed builder
	 */
	public BuilderPrinter(final ClassMirror classMirror, final Context context) {
		super(classMirror, context);
	}

	@Override
	public final String getFullClassName() {
		return getPackageName() + "." + getClassMirror().getSimpleName() + getContext().getBuilderClassPostfix();
	}

	@Override
	protected final void printClassWithBody() {

		printLine("public abstract class #0 extends #1#0<#0> {", builderName(), getContext()
																							.getAbstractBuilderClassPrefix());

		printCreateMethod();
		printLine("}");
	}

	@Override
	protected final Set<String> getFullClassNamesForImports() {
		final Set<String> fullClassNames = new TreeSet<String>();
		if (getContext().isStaticCreate()) {
			fullClassNames.add(AbstractBuilderFactory.class.getCanonicalName());
		}
		return fullClassNames;
	}

	@Override
	public final String getPackageName() {
		return getClassMirror().getPackageName();
	}

	@Override
	protected final void printClassComment() {
		printLine("/**");
		printLine(" * Fluent builder for #0.", getClassMirror().getSimpleName());
		printLine(" * Don't hesitate to put your custom methods here.");
		printLine(" */");
	}

	@Override
	public final String builderName() {
		return getClassMirror().getSimpleName() + getContext().getBuilderClassPostfix();
	}

}
