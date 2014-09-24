/*
 * Created on 24 wrz 2014 18:21:59 by Andrzej Ludwikowski
 */

package info.ludwikowski.fluentbuilder.model;

import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.util.SimpleTypeVisitor6;


public class ConstructorGeneratorVisitor extends SimpleTypeVisitor6<Constructor, Element> {

	@Override
	public Constructor visitExecutable(ExecutableType t, Element p) {
		// TODO Auto-generated method stub
		return super.visitExecutable(t, p);
	}

	/**
	 * 
	 * 
	 * @Override
	 *           Creates a {@link MemberMirror} for a {@link Element} which is of a {@link ExecutableType}.
	 *           This includes methods and constructors.
	 * @return MemberMirror representation of the given method or constructor
	 *         public final MemberMirror visitExecutable(final ExecutableType executableType, final Element element) {
	 *         final List<ParameterMirror> parameterList = getParameterList(executableType, element);
	 *         final String name = createMethodNameFromParameterNames(parameterList);
	 *         final Set<String> imports = new TreeSet<String>();
	 * 
	 *         // for (ParameterMirror parameterMirror : parameterList) {
	 *         // imports.addAll(ImportsFactory.createNecessaryImportsForTypeInClass(
	 *         // parameterMirror.getType().toString()));
	 *         // }
	 * 
	 *         // return new MemberMirrorConstructorImpl(name, ownerName, parameterList, imports);
	 *         return null;
	 *         }
	 * 
	 *         private List<ParameterMirror> getParameterList(final ExecutableType executableType, final Element
	 *         element) {
	 *         final List<? extends VariableElement> parameterNames = ((ExecutableElement) element).getParameters();
	 *         final List<? extends TypeMirror> parameterTypes = executableType.getParameterTypes();
	 *         final List<ParameterMirror> parameters = new ArrayList<ParameterMirror>();
	 *         int counter = 0;
	 *         for (VariableElement parameterName : parameterNames) {
	 *         parameters.add(new ParameterMirror(parameterTypes.get(counter), parameterName.toString()));
	 *         counter++;
	 *         }
	 *         return parameters;
	 *         }
	 * 
	 *         private String createMethodNameFromParameterNames(final List<ParameterMirror> parameterList) {
	 *         final StringBuffer methodName = new StringBuffer();
	 *         for (ParameterMirror parameterMirror : parameterList) {
	 *         methodName.append(StringUtils.capitalize(parameterMirror.getName()));
	 *         methodName.append("And");
	 *         }
	 *         if (methodName.length() >= CONJUNCTION_LENGTH) {
	 *         methodName.delete(methodName.length() - CONJUNCTION_LENGTH, methodName.length());
	 *         }
	 *         return methodName.toString();
	 *         }
	 */
}
