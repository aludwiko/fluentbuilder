/*
 * Created on 10-03-2013 11:16:45 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */

package info.ludwikowski.fluentbuilder.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import de.bluecarat.fluentbuilder.model.MemberMirrorConstructorImpl;
import de.bluecarat.fluentbuilder.model.ParameterMirror;

import info.ludwikowski.fluentbuilder.model.ClassMirror;
import info.ludwikowski.fluentbuilder.model.MemberMirror;
import info.ludwikowski.fluentbuilder.model.MemberMirrorImpl;
import info.ludwikowski.fluentbuilder.util.TypeUtils;

/**
 * This class generates the source code of an abstract builder class from a
 * {@link ClassMirror}.
 * @author Andrzej Ludwikowski
 * @author Jan van Esdonk
 */
public class PrinterForAbstractBuilder extends AbstractClassPrinter {

    private static final String SYMBOL_FOR_BUILDER_GENERIC = "B";
    private final List<String> usedMembers;

    /**
     * Constructor for an AbstractBuilderPrinter.
     * @param classMirror - stores information about the class for which the
     *            builder is generated
     * @param context - stores the settings for the generated sources
     */
    public PrinterForAbstractBuilder(final ClassMirror classMirror, final Context context) {
        super(classMirror, context);
        usedMembers = new ArrayList<String>();
    }

    private String abstractBuilderClassName() {
        return getClassMirror().getSimpleName() + getContext().getBuilderClassPostfix();
    }

    @Override
    protected final void printClassWithBody() {
        printBuilderBegin();
        printBuilderMethodPrefixGetter();
        printBuilderBuildMethod();
        printBuilderBody();
        printBuilderEnd();
    }

    protected final void printBuilderBuildMethod() {
        if (getContext().getBuildMethodName() != null) {
            printLine();
            increaseIndentation();
            printLine("public #0 #1(){", getClassMirror().getSimpleName(), getContext().getBuildMethodName());
            increaseIndentation();
            printLine("return build();");
            decreaseIndentation();
            printBuilderEnd();
            decreaseIndentation();
        }
    }

    protected final void printBuilderMethodPrefixGetter() {
        if (!AbstractBuilder.BUILDER_METHOD_PREFIX.equals(getContext().getMethodPrefix())) {
            printLine();
            increaseIndentation();
            printLine("public static final String getPrefix() {");
            increaseIndentation();
            printLine("return \"#0\";", getContext().getMethodPrefix());
            decreaseIndentation();
            printBuilderEnd();
            decreaseIndentation();
        }
    }

    protected final void printBuilderBody() {

        increaseIndentation();
        printLine();

        final List<MemberMirror> mirrors = getClassMirror().getMembers();

        printAbstractMethods(mirrors);

        decreaseIndentation();
    }

    private void printAbstractMethods(final List<MemberMirror> mirrors) {
        for (final MemberMirror memberMirror : mirrors) {
            if (memberMirror instanceof MemberMirrorImpl) {
                printAbstractMethodForMemberField(memberMirror);
            } else if (memberMirror instanceof MemberMirrorConstructorImpl) {
                printAbstractMethodForConstructor((MemberMirrorConstructorImpl) memberMirror);
            }
        }
    }

    private void printAbstractMethodForConstructor(final MemberMirrorConstructorImpl memberMirror) {
        final String parameterSignature = createSignatureForParameters(memberMirror);
        printLine("public abstract #0 #1#2(#3);", abstractBuilderReturnName(), getContext()
            .getConstructorMethodPrefix(), memberMirror.getName(), parameterSignature);
    }

    private String createSignatureForParameters(final MemberMirrorConstructorImpl memberMirror) {
        final List<ParameterMirror> parameters = memberMirror.getParameters();
        final StringBuffer parameterSignature = new StringBuffer();
        for (ParameterMirror parameterMirror : parameters) {
            parameterSignature.append(info.ludwikowski.fluentbuilder.util.NameUtils
                .removePackageNameFromFullyQualifiedName(parameterMirror.getType().toString()));
            parameterSignature.append(" ");
            parameterSignature.append(parameterMirror.getName());
            parameterSignature.append(", ");
        }
        if (parameterSignature.length() >= 1) {
            parameterSignature.delete(parameterSignature.length() - 2, parameterSignature.length());
        }
        return parameterSignature.toString();
    }

    private void printAbstractMethodForMemberField(final MemberMirror mirror) {
        final String fieldName = mirror.getName();
        final String uniqueName = getUniqueName(mirror);
        final String annotationValue = mirror.getOwnerName() + "." + fieldName;
        printReferencedFieldAnnotation(annotationValue);
        printLine(
                "public abstract #0 #1#2(#3 #4);",
                abstractBuilderReturnName(),
                getContext().getMethodPrefix(),
                StringUtils.capitalize(uniqueName),
                mirror.getSimpleType(),
                fieldName);
        if (mirror.getCollectionType() != null) {
            printVarargsSetter(mirror, uniqueName);
        }
        usedMembers.add(uniqueName);
    }

    private void printVarargsSetter(final MemberMirror mirror, final String uniqueName) {
        final String annotationValue = mirror.getOwnerName() + "." + mirror.getName();
        printReferencedFieldAnnotation(annotationValue);
        printLine(
                "public #0 #1#2(#3... #4){",
                abstractBuilderReturnName(),
                getContext().getMethodPrefix(),
                StringUtils.capitalize(uniqueName),
                mirror.getCollectionElementSimpleName(),
                mirror.getName());
        increaseIndentation();
        printCollectionCreation(mirror, uniqueName);
        decreaseIndentation();
        printBuilderEnd();
    }

    @Override
    public final String getFullClassName() {
        return getPackageName() + "." + builderName();
    }

    private void printCollectionCreation(final MemberMirror memberMirror, final String suffixedName) {

        if (TypeUtils.isList(memberMirror.getCollectionType())) {
            printLine(
                    "return #0#1(new ArrayList<#2>(Arrays.asList(#3)));",
                    getContext().getMethodPrefix(),
                    StringUtils.capitalize(suffixedName),
                    memberMirror.getCollectionElementSimpleName(),
                    memberMirror.getName());
        } else if (TypeUtils.isSet(memberMirror.getCollectionType())) {
            printLine(
                    "return #0#1(new HashSet<#2>(Arrays.asList(#3)));",
                    getContext().getMethodPrefix(),
                    StringUtils.capitalize(suffixedName),
                    memberMirror.getCollectionElementSimpleName(),
                    memberMirror.getName());
        }
    }

    private String getUniqueName(final MemberMirror mirror) {
        final String nameWithPrefix = getSuperPrefix(mirror) + StringUtils.capitalize(mirror.getName());
        return addSuffix(nameWithPrefix);
    }

    private String getSuperPrefix(final MemberMirror mirror) {
        final String fieldName = mirror.getName();
        if (!mirror.getSimpleOwnerName().equals(getClassMirror().getSimpleName())
                && usedMembers.contains(StringUtils.capitalize(fieldName))) {
            return mirror.getSimpleOwnerName();
        }
        return "";
    }

    private String addSuffix(final String memberNameWithPrefix) {
        final String fieldName = memberNameWithPrefix;
        if (usedMembers.contains(fieldName)) {
            final String fieldNameWithSuffix = fieldName + "_";
            if (usedMembers.contains(fieldNameWithSuffix)) {
                addSuffix(fieldNameWithSuffix);
            } else {
                return fieldNameWithSuffix;
            }
        }
        return memberNameWithPrefix;
    }

    private void printReferencedFieldAnnotation(final String annotationValue) {
        if (annotationValue != null) {
            printLine("@ReferencedField(\"#0\")", annotationValue);
        }
    }

    protected final void printBuilderEnd() {
        printLine("}");
    }

    protected final void printBuilderBegin() {
        printLine(
                "public abstract class #0 extends AbstractBuilder<#1, #2> {",
                abstractBuilderFullClassName(),
                getClassMirror().getSimpleName(),
                SYMBOL_FOR_BUILDER_GENERIC);
    }

    private String abstractBuilderFullClassName() {
        return getContext().getAbstractBuilderClassPrefix() + abstractBuilderClassName() + "<B>";
    }

    protected final String abstractBuilderReturnName() {
        return SYMBOL_FOR_BUILDER_GENERIC;
    }

    @Override
    public final String builderName() {
        return getContext().getAbstractBuilderClassPrefix() + abstractBuilderClassName();
    }

    @Override
    protected final Set<String> getFullClassNamesForImports() {

        final Set<String> imports = new TreeSet<String>();

        imports.add(AbstractBuilder.class.getCanonicalName());
        imports.addAll(getClassMirror().getImports());

        return imports;
    }

    @Override
    public final String getPackageName() {
        return getClassMirror().getPackageName();
    }

    @Override
    protected final void printClassComment() {
        printLine("/** ");
        printLine(" * Abstract builder for #0. ", getClassMirror().getSimpleName());
        printLine(
                " * After changes in #0 this class will be overridden, so don't put any changes here, use #1 instead.",
                getClassMirror().getSimpleName(),
                abstractBuilderClassName());
        printLine(" */");
    }

}
