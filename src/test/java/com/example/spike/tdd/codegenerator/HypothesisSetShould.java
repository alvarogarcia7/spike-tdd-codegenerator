package com.example.spike.tdd.codegenerator;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HypothesisSetShould {

	private HypothesisSet sut;

	@Test
	public void find_the_identity () {
		final int input = 2;
		final int output = 2;
		sut = new HypothesisSet(Arrays.asList(new Hypothesis(Arrays.asList(input), output)));

		final Object formula = sut.findFormula();

		assertThat(((Function) formula).apply(input), is(output));
	}

	@Test
	public void find_the_successor () {
		final int output = 3;
		final int input = 2;
		sut = new HypothesisSet(Arrays.asList(new Hypothesis(Arrays.asList(input), output)));

		final Object formula = sut.findFormula();

		assertThat(((Function) formula).apply(input), is(output));
	}

}
