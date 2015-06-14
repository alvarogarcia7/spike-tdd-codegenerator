package com.example.spike.tdd.codegenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class HypothesisSetShould {

	private HypothesisSet sut;

	@Before
	public void setUp () {
		sut = new HypothesisSet(Arrays.asList(new Hypothesis(Arrays.asList(2), 2)));
	}

	@Test
	public void find_the_identity () {

		assertThat(sut.findFormula(), is(not(nullValue())));

	}

}
