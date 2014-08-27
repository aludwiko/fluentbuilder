/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

/**
 * @author Jan van Esdonk
 */
public class ImportsFactoryUnitTest {

    @Test
    public void shouldCreateImportForClassInParentPackage() {
        final String type = "de.bluecarat.gpms.model.Usage";
        final String className = "de.bluecarat.gpms.model.module.AbstractPermissionModuleBuilder";
        final Collection<String> testImports = ImportsFactory.createNecessaryImportsForTypeInClass(type, className);
        assertThat(testImports.toString(), equalTo("[de.bluecarat.gpms.model.Usage]"));
    }

}
