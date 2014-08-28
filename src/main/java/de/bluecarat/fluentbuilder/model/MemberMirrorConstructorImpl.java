/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.model;

import java.util.List;
import java.util.Set;

import info.ludwikowski.fluentbuilder.model.Imports;
import info.ludwikowski.fluentbuilder.model.MemberMirror;
import info.ludwikowski.fluentbuilder.util.NameUtils;

/**
 * This class defines a MemberMirror which represents a constructor of a class.
 * 
 * @author Jan van Esdonk
 */
public class MemberMirrorConstructorImpl implements MemberMirror {

	private final String name;
	private final String ownerName;
	private final List<ParameterMirror> parameters;
	private final Imports imports = new Imports();


	/**
	 * Constructs a standard MemberMirrorConstructorImpl.
	 * 
	 * @param name - generated constructor name
	 * @param ownerName - owning class
	 * @param parameters - constructor parameters
	 * @param imports - imports which are needed for the constructor
	 */
	public MemberMirrorConstructorImpl(final String name, final String ownerName,
			final List<ParameterMirror> parameters, final Set<String> imports) {
		this.name = name;
		this.ownerName = ownerName;
		this.parameters = parameters;
		this.imports.addAll(imports);
	}

	@Override
	public final String getCollectionType() {
		return null;
	}

	@Override
	public final String getSimpleType() {
		return null;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final Imports getImports() {
		return imports;
	}

	@Override
	public final boolean isSupportedVarArgsCollection() {
		return false;
	}

	@Override
	public final String getCollectionElementSimpleName() {
		return null;
	}

	@Override
	public final String getOwnerName() {
		return ownerName;
	}

	@Override
	public final String getSimpleOwnerName() {
		return NameUtils.removePackageNameFromFullyQualifiedName(ownerName);
	}

	/**
	 * Returns a List of ParameterMirrors.
	 * 
	 * @return - List of ParameterMirrors.
	 */
	public final List<ParameterMirror> getParameters() {
		return parameters;
	}

}
