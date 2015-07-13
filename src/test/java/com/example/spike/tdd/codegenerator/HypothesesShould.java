package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.hypothesis.Hypotheses;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HypothesesShould {

	private Hypotheses sut;
	private List specifications;
	private Specification specification1;
	private Specification specification2;

	@Before
	public void setUp () {
		specification1 = mock(Specification.class);
		specification2 = mock(Specification.class);
		specifications = Arrays.asList(specification1, specification2);
		sut = new Hypotheses(specifications);
	}

	@Test
	public void ask_all_the_specifications_for_the_hypothesis () {

		sut.getHypotheses();

		verify(specification1).getHypothesis();
		verify(specification2).getHypothesis();

	}

}
