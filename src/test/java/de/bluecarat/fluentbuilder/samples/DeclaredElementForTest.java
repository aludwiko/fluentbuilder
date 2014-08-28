/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package de.bluecarat.fluentbuilder.samples;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

/**
 * @author Jan van Esdonk
 */
public class DeclaredElementForTest implements Element {

	public Element owner;


	public DeclaredElementForTest() {
		owner = mock(Element.class);
		when(owner.toString()).thenReturn("java.Object");
	}

	@Override
	public TypeMirror asType() {
		return null;
	}

	@Override
	public ElementKind getKind() {
		return null;
	}

	@Override
	public List<? extends AnnotationMirror> getAnnotationMirrors() {
		return null;
	}

	@Override
	public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
		return null;
	}

	@Override
	public Set<Modifier> getModifiers() {
		return null;
	}

	@Override
	public Name getSimpleName() {
		return null;
	}

	@Override
	public Element getEnclosingElement() {
		return null;
	}

	@Override
	public List<? extends Element> getEnclosedElements() {
		return null;
	}

	@Override
	public <R, P> R accept(ElementVisitor<R, P> v, P p) {
		return null;
	}

	@Override
	public String toString() {
		return "declaredField";
	}

}
