/*
 * Created by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.model;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.lang.model.type.TypeMirror;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.bluecarat.fluentbuilder.model.MemberMirrorConstructorImpl;
import de.bluecarat.fluentbuilder.model.ParameterMirror;

/**
 * Dummy test case to "ignore" getter and setters in test coverage checks
 * 
 * @author Jan van Esdonk
 */
public class GetterAndSetterDummyTest {

	@Mock
	TypeMirror mockedTypeMirror;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void dummyTest() {
		MemberMirrorConstructorImpl dummyConstructorMirror = new MemberMirrorConstructorImpl(
				"",
				"",
				new ArrayList<ParameterMirror>(),
				new TreeSet<String>());
		dummyConstructorMirror.getName();
		dummyConstructorMirror.getOwnerName();
		dummyConstructorMirror.getParameters();
		ParameterMirror dummyParameterMirror = new ParameterMirror(mockedTypeMirror, "int");
		dummyParameterMirror.getName();
		dummyParameterMirror.getType();
	}

}
