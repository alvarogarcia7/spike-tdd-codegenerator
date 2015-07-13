package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.specification.Specification;
import com.example.spike.tdd.codegenerator.specification.Specifications;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HypothesesShould {

	private Specifications sut;
	private List specifications;
	private Specification specification1;
	private Specification specification2;

	@Before
	public void setUp () {
		specification1 = mock(Specification.class);
		specification2 = mock(Specification.class);
		specifications = Arrays.asList(specification1, specification2);
		sut = new Specifications(specifications);
	}

	@Test
	public void ask_all_the_specifications_for_the_hypothesis () {

		sut.getApplications();

		verify(specification1).getApplication();
		verify(specification2).getApplication();

	}

}
