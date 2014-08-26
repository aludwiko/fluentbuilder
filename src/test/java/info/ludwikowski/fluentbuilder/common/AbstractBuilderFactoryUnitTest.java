/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.common;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.bluecarat.fluentbuilder.samples.ArgTestObject;
import de.bluecarat.fluentbuilder.samples.ArgTestObjectBuilder;
import de.bluecarat.fluentbuilder.samples.BaseTestObject;
import de.bluecarat.fluentbuilder.samples.BaseTestObjectBuilder;

/**
 * @author Jan van Esdonk
 */
public class AbstractBuilderFactoryUnitTest {

    @Test
    public void shouldCreateImplementation() {
        // given
        Class<BaseTestObjectBuilder> abstractBuilderClass = BaseTestObjectBuilder.class;

        // when
        final BaseTestObjectBuilder testBuilder = (BaseTestObjectBuilder) AbstractBuilderFactory.createImplementation(
                abstractBuilderClass).builder();
        final BaseTestObject testObject = (BaseTestObject) AbstractBuilderFactory.createImplementation(
                abstractBuilderClass).build();

        // then
        assertThat(testBuilder, is(instanceOf(BaseTestObjectBuilder.class)));
        assertThat(testObject, is(instanceOf(BaseTestObject.class)));
    }

    @Test
    public void shouldCreateImplementationWithDummyArgs() {
        // given
        Class<ArgTestObjectBuilder> abstractBuilderClass = ArgTestObjectBuilder.class;

        // when
        final ArgTestObjectBuilder testBuilder = (ArgTestObjectBuilder) AbstractBuilderFactory
            .createImplementation(abstractBuilderClass);
        final ArgTestObject testObject = (ArgTestObject) AbstractBuilderFactory.createImplementation(
                abstractBuilderClass).build();

        // then
        assertThat(testBuilder, is(instanceOf(ArgTestObjectBuilder.class)));
        assertThat(testObject.isBooleanField(), is(false));
        assertThat(testObject.getIntField(), is(0));
        assertThat(testObject.getShortField(), is((short) 0));
        assertThat(testObject.getFloatField(), is((float) 0));
        assertThat(testObject.getDoubleField(), is((double) 0));
        assertThat(testObject.getLongField(), is((long) 0));
        assertThat(testObject.getCharField(), is((char) 0));
        assertThat(testObject.getByteField(), is((byte) 0));
        assertThat(testObject.getObjectField(), nullValue());
        assertThat(testObject.isCreatedByShortest(), is(true));
    }
}
