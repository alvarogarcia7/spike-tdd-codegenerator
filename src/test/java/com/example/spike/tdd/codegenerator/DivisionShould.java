package com.example.spike.tdd.codegenerator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DivisionShould {



	@Test
	public void not_fail_if_the_first_output_is_zero () {

		final Hypothesis hypothesis1 = buildHypothesis(0, 1);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

	@Test
	public void not_fail_if_the_any_output_is_zero () {

		final int input1 = 1;
		final int output1 = 1;
		final Hypothesis hypothesis1 = buildHypothesis(output1, input1);
		final Hypothesis hypothesis2 = buildHypothesis(0, input1);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1, hypothesis2);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

	private Hypothesis buildHypothesis (Object output, Object input) {
		return new Hypothesis(Arrays.asList(input), output);
	}
}
