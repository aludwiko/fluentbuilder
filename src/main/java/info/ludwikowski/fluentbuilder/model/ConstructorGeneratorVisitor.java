/*
 * Created on 24 wrz 2014 18:21:59 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.model;

import java.util.logging.Logger;

import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.util.SimpleTypeVisitor6;


public class ConstructorGeneratorVisitor extends SimpleTypeVisitor6<Constructor, Element> {

	private static final Logger LOGGER = Logger.getLogger(ConstructorGeneratorVisitor.class.getName());


	@Override
	public Constructor visitExecutable(ExecutableType t, Element p) {
		return Constructor.create(t, p);
	}
}
