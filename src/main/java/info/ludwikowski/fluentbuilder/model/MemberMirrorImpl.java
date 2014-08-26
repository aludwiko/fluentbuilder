/*
 * Created on 22-03-2013 20:42:27 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import java.util.Set;

import info.ludwikowski.fluentbuilder.util.Constants;
import info.ludwikowski.fluentbuilder.util.NameUtils;

/**
 * This class implement the MemberMirror interface. A MemberMirrorImpl has the
 * ability to determine its owning class.
 * @author Andrzej Ludwikowski
 */
public final class MemberMirrorImpl implements MemberMirror {

    private final String simpleType;
    private final String name;
    private final String ownerName;
    private final String simpleOwnerName;
    private final Imports imports = new Imports();
    private final String collectionType;
    private final String collectionElementSimpleName;

    private MemberMirrorImpl(final String name, final String ownerName, final String simpleType,
                             final Set<String> imports, final String collectionType,
                             final String collectionElementSimpleName) {
        this.name = name;
        this.ownerName = ownerName;
        this.simpleOwnerName = NameUtils.removePackageNameFromFullyQualifiedName(ownerName);
        this.collectionType = collectionType;
        this.simpleType = simpleType;
        this.collectionElementSimpleName = collectionElementSimpleName;
        this.imports.addAll(imports);
    }

    /**
     * Creates a MemberMirror for a collection type.
     * @param name - field name
     * @param ownerName - owning class
     * @param simpleType - short type name of the mirror
     * @param imports - necessary imports
     * @param collectionType - type of the mirrored collection
     * @param collectionElementSimpleName - simple collection name
     * @return MemberMirror instance
     */
    public static MemberMirror collectionMirror(final String name, final String ownerName, final String simpleType,
                                                final Set<String> imports, final String collectionType,
                                                final String collectionElementSimpleName) {
        return new MemberMirrorImpl(name, ownerName, simpleType, imports, collectionType, collectionElementSimpleName);
    }

    /**
     * Creates a MemberMirror for a simple type.
     * @param name - field name
     * @param ownerName - owning class
     * @param simpleType - short type name of the mirror
     * @param imports - necessary imports
     * @return MemberMirror instance
     */
    public static MemberMirror simpleMirror(final String name, final String ownerName, final String simpleType,
                                            final Set<String> imports) {
        return new MemberMirrorImpl(name, ownerName, simpleType, imports, null, null);
    }

    @Override
    public boolean isSupportedVarArgsCollection() {

        if (collectionType == null) {
            return false;
        }

        return Constants.SUPPORTED_VARARGS_COLLECTIONS.contains(collectionType);
    }

    @Override
    public Imports getImports() {
        return imports;
    }

    @Override
    public String getSimpleType() {
        return simpleType;
    }

    @Override
    public String getCollectionType() {
        return collectionType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCollectionElementSimpleName() {
        return collectionElementSimpleName;
    }

    // Generated method
    // CHECKSTYLE IGNORE CyclomaticComplexity FOR NEXT 2 LINES
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1 * prime;
        result = prime * result;
        if (collectionElementSimpleName != null) {
            result = result + collectionElementSimpleName.hashCode();
        }
        result = prime * result;
        if (collectionType != null) {
            result = result + collectionType.hashCode();
        }
        result = prime * result;
        if (name != null) {
            result = result + name.hashCode();
        }
        result = prime * result;
        if (simpleType != null) {
            result = result + simpleType.hashCode();
        }
        return result;
    }

    // Generated method
    // CHECKSTYLE IGNORE CyclomaticComplexity|NCSS|NPathComplexityCheck|MethodLength FOR NEXT 4 LINES
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemberMirrorImpl other = (MemberMirrorImpl) obj;
        if (collectionElementSimpleName == null) {
            if (other.collectionElementSimpleName != null) {
                return false;
            }
        } else if (!collectionElementSimpleName.equals(other.collectionElementSimpleName)) {
            return false;
        }
        if (collectionType == null) {
            if (other.collectionType != null) {
                return false;
            }
        } else if (!collectionType.equals(other.collectionType)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (simpleType == null) {
            if (other.simpleType != null) {
                return false;
            }
        } else if (!simpleType.equals(other.simpleType)) {
            return false;
        }
        return true;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String getSimpleOwnerName() {
        return simpleOwnerName;
    }
}
