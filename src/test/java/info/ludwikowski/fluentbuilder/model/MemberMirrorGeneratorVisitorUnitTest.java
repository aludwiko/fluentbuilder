/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.when;
import info.ludwikowski.fluentbuilder.processor.ProcessorContext;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.util.Types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Jan van Esdonk
 */
@RunWith(MockitoJUnitRunner.class)
public class MemberMirrorGeneratorVisitorUnitTest {

	private MemberMirrorGeneratorVisitor testVisitor = null;
	private ProcessorContext mockedContext;
	@Mock
	private PrimitiveType mockedPrimitiveType;
	@Mock
	private DeclaredType mockedDeclaredType;
	@Mock
	private Types mockedType;
	@Mock
	private TypeElement mockedTypeElement;


	@Before
	public void setUp() throws Exception {
		mockedContext = Mockito.mock(ProcessorContext.class);
		testVisitor = new MemberMirrorGeneratorVisitor(mockedContext);
	}

	@Test
	public void shouldCreateMemberMirrorWithNameFromPrimitiveElement() {
		// given
		final Element primitiveElement = new PrimitiveElementMock();
		// when
		final MemberMirror testMember = testVisitor.visitPrimitive(mockedPrimitiveType, primitiveElement);
		// then
		assertThat(testMember.getName(), containsString("intField"));
	}

	@Test
	public void shouldCreateMemberMirrorWithSimpleTypeFromPrimitiveElement() {
		// given
		when(mockedPrimitiveType.toString()).thenReturn("int");
		final Element primitiveElement = new PrimitiveElementMock();
		// when
		final MemberMirror testMember = testVisitor.visitPrimitive(mockedPrimitiveType, primitiveElement);
		// then
		assertThat(testMember.getSimpleType(), containsString("int"));
	}

	@Test
	public void shouldCreateMemberMirrorWithNameFromDeclaredElement() {
		// given
		final Element element = new DeclaredElementMock();
		when(mockedContext.getTypeUtils()).thenReturn(mockedType);
		when(mockedType.asElement(mockedDeclaredType)).thenReturn(mockedTypeElement);
		// when
		final MemberMirror testMember = testVisitor.visitDeclared(mockedDeclaredType, element);
		// then
		assertThat(testMember.getName(), containsString("declaredField"));
	}

	@Test
	public void shouldCreateMemberMirrorWithSimpleTypeFromDeclaredElement() {
		// given
		final Element element = new DeclaredElementMock();
		when(mockedContext.getTypeUtils()).thenReturn(mockedType);
		when(mockedType.asElement(mockedDeclaredType)).thenReturn(mockedTypeElement);
		when(mockedDeclaredType.toString()).thenReturn("String");
		// when
		final MemberMirror testMember = testVisitor.visitDeclared(mockedDeclaredType, element);
		// then
		assertThat(testMember.getSimpleType(), containsString("String"));
	}
}
