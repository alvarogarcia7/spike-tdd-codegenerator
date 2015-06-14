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
		sut = new HypothesisSet(Arrays.asList(new Hypothesis(Arrays.asList(2), 2)));

		final Object formula = sut.findFormula();

		assertThat(((Function) formula).apply(1), is(1));
	}

	@Test
	public void find_the_successor () {
		sut = new HypothesisSet(Arrays.asList(new Hypothesis(Arrays.asList(2), 3)));

		final Object formula = sut.findFormula();

		assertThat(((Function) formula).apply(1), is(2));
	}

}
