/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import de.bluecarat.fluentbuilder.samples.BaseTestObject;
import de.bluecarat.fluentbuilder.samples.ExtendsTestObject;

import info.ludwikowski.fluentbuilder.common.Context;

/**
 * @author Jan van Esdonk
 */
public class ClassMirrorImplUnitTest {

	@Test
	public void shouldAddInheritatedMemberMirrors() {
		// given
		ClassMirrorImpl testClassMirror = new ClassMirrorImpl(ExtendsTestObject.class, new Context());
		// when
		List<MemberMirror> members = testClassMirror.getMembers();
		// then
		MemberMirror inheritatedMemberMirror = members.get(1);
		assertThat(inheritatedMemberMirror.getName(), equalTo("uniqueExtendsField"));
	}

	@Test
	public void shouldFillMemberMirrorsFromClass() {
		ClassMirrorImpl testClassMirror = new ClassMirrorImpl(BaseTestObject.class, new Context());
		List<MemberMirror> members = testClassMirror.getMembers();
		assertThat(members.size(), is(10));
	}

}
