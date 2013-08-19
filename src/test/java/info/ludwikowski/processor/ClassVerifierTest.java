/* 
 * Created on 18-03-2013 18:37:00 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import static info.ludwikowski.processor.ProcessorContext.JAVAX_PERSISTENCE_EMBEDDABLE;
import static info.ludwikowski.processor.ProcessorContext.JAVAX_PERSISTENCE_ENTITY;
import static info.ludwikowski.processor.ProcessorContext.JAVAX_PERSISTENCE_MAPPEDSUPERCLASS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;


@RunWith(JUnitParamsRunner.class)
public class ClassVerifierTest {

	@InjectMocks
	private ClassVerifier classVerifier;

	@Mock
	private ProcessorContext context;

	@Test
	public void shouldReturnTrueForElementAnnotatedWithProcessorAnnotation() {

		// given
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);
		String type = "info.ludwikowski.annotation.GenerateBuilder";

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(type);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@Parameters({ JAVAX_PERSISTENCE_EMBEDDABLE, JAVAX_PERSISTENCE_ENTITY, JAVAX_PERSISTENCE_MAPPEDSUPERCLASS })
	public void shouldReturnTrueForElementAnnotatedWithJPAAnnotation(String jpaAnnotation) {

		// given
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(jpaAnnotation);

		given(context.isAcceptJavaPersisentceAnnotations()).willReturn(true);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnFalseForOtherAnnotations() {

		// given
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);
		String type = "something.GenerateBuilder";

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(type);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isFalse();
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
}
