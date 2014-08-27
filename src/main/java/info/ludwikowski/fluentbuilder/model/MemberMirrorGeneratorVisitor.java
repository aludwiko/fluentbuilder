/*
 * Created on 03-04-2013 18:36:02 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import info.ludwikowski.fluentbuilder.processor.ProcessorContext;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor6;

import org.apache.commons.lang.StringUtils;

import de.bluecarat.fluentbuilder.model.MemberMirrorConstructorImpl;
import de.bluecarat.fluentbuilder.model.ParameterMirror;

/**
 * A visitor for creating MemberMirrors by visiting Java elements.
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public class MemberMirrorGeneratorVisitor extends SimpleTypeVisitor6<MemberMirror, Element> {

    private static final int CONJUNCTION_LENGTH = 3;
    private final ProcessorContext context;

    /**
     * Creates a MemberMirrorGeneratorVisitor with given settings.
     * @param context - inhabits MirrorCreation settings
     */
    public MemberMirrorGeneratorVisitor(final ProcessorContext context) {
        this.context = context;
    }

    @Override
    /**
     * Creates a simple {@link MemberMirror} for a {@link Element} which is of a {@link PrimitiveType}.
     * This includes fields which are of a primitive type.
     * @return MemberMirror representation of the primitive field
     */
    public final MemberMirror visitPrimitive(final PrimitiveType primitiveType, final Element element) {
        return simpleTypes(primitiveType, element);
    }

    @SuppressWarnings("unchecked")
    private MemberMirror simpleTypes(final TypeMirror primitiveType, final Element element) {

        final String name = element.toString();
        final String ownerName = getOwnerName(element);
        final String simpleType = info.ludwikowski.fluentbuilder.util.NameUtils
            .removePackageNameFromFullyQualifiedName(primitiveType.toString());

        return MemberMirrorImpl.simpleMirror(name, ownerName, simpleType, Collections.EMPTY_SET);
    }

    @Override
    /**
     * Creates a simple {@link MemberMirror} for a {@link Element} which is of a {@link ArrayType}.
     * This include fields which are Arrays.
     * @return MemberMirror representation of the array field
     */
    public final MemberMirror visitArray(final ArrayType t, final Element element) {
        return simpleTypes(t, element);
    }

    @Override
    /**
     * Creates a {@link MemberMirror} for a {@link Element} which is of a {@link DeclaredType}.
     * This include fields which are not of a primitive type.
     * @return MemberMirror representation of the declared field
     */
    public final MemberMirror visitDeclared(final DeclaredType declaredType, final Element element) {

        final TypeElement returnedElement = (TypeElement) context.getTypeUtils().asElement(declaredType);

        final String name = element.toString();
        final String ownerName = getOwnerName(element);
        final String simpleType = info.ludwikowski.fluentbuilder.util.NameUtils
            .removePackageNameFromFullyQualifiedName(declaredType.toString());
        final Set<String> imports = getImports(declaredType, returnedElement, ownerName);
        final String type = returnedElement.toString();

        if (TypeUtils.isListOrSet(type) && isGeneric(declaredType)) {

            return MemberMirrorImpl.collectionMirror(
                    name,
                    ownerName,
                    simpleType,
                    imports,
                    type,
                    collectionElementSimpleName(declaredType));
        }

        return MemberMirrorImpl.simpleMirror(name, ownerName, simpleType, imports);
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private String getOwnerName(final Element ownedElement) {
        try {
            final Field field = ownedElement.getClass().getField("owner");
            field.setAccessible(true);
            final Object truncatedOwner = field.get(ownedElement);
            return truncatedOwner.toString();
		}
		catch (NoSuchFieldException e) {
			throw new RuntimeException("No access on owner field possible.", e);
        }
		catch (IllegalArgumentException e) {
			throw new RuntimeException("No access on owner field possible.", e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException("No access on owner field possible.", e);
		}
    }

    private boolean isGeneric(final DeclaredType declaredType) {
        return !declaredType.getTypeArguments().isEmpty();
    }

    private String collectionElementSimpleName(final DeclaredType declaredType) {

        return info.ludwikowski.fluentbuilder.util.NameUtils.removePackageNameFromFullyQualifiedName(declaredType
            .getTypeArguments().get(0).toString());
    }

    private Set<String> getImports(final DeclaredType declaredType, final TypeElement returnedElement,
                                   final String declaringClassName) {

        final Set<String> imports = new TreeSet<String>();
        final String type = returnedElement.toString();

        imports.addAll(ImportsFactory.createNecessaryImportsForTypeInClass(type, declaringClassName));

        for (final TypeMirror typeMirror : declaredType.getTypeArguments()) {
            imports.addAll(ImportsFactory.createNecessaryImportsForTypeInClass(
                    typeMirror.toString(),
                    declaringClassName));
        }
        return imports;
    }

    @Override
    /**
     * Creates a {@link MemberMirror} for a {@link Element} which is of a {@link ExecutableType}.
     * This includes methods and constructors.
     * @return MemberMirror representation of the given method or constructor
     */
    public final MemberMirror visitExecutable(final ExecutableType executableType, final Element element) {
        final String ownerName = getOwnerName(element);
        final List<ParameterMirror> parameterList = getParameterList(executableType, element);
        final String name = createMethodNameFromParameterNames(parameterList);
        final Set<String> imports = new TreeSet<String>();

        for (ParameterMirror parameterMirror : parameterList) {
            imports.addAll(ImportsFactory.createNecessaryImportsForTypeInClass(
                    parameterMirror.getType().toString(),
                    getOwnerName(element)));
        }

        return new MemberMirrorConstructorImpl(name, ownerName, parameterList, imports);
    }

    private List<ParameterMirror> getParameterList(final ExecutableType executableType, final Element element) {
        final List<? extends VariableElement> parameterNames = ((ExecutableElement) element).getParameters();
        final List<? extends TypeMirror> parameterTypes = executableType.getParameterTypes();
        final List<ParameterMirror> parameters = new ArrayList<ParameterMirror>();
        int counter = 0;
        for (VariableElement parameterName : parameterNames) {
            parameters.add(new ParameterMirror(parameterTypes.get(counter), parameterName.toString()));
            counter++;
        }
        return parameters;
    }

    private String createMethodNameFromParameterNames(final List<ParameterMirror> parameterList) {
        final StringBuffer methodName = new StringBuffer();
        for (ParameterMirror parameterMirror : parameterList) {
            methodName.append(StringUtils.capitalize(parameterMirror.getName()));
            methodName.append("And");
        }
        if (methodName.length() >= CONJUNCTION_LENGTH) {
            methodName.delete(methodName.length() - CONJUNCTION_LENGTH, methodName.length());
        }
        return methodName.toString();
    }
}
