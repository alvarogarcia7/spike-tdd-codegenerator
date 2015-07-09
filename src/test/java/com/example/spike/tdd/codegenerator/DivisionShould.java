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

		final int input1 = 1;
		final int output1 = 0;
		final Hypothesis hypothesis1 = new Hypothesis(Arrays.asList(input1), output1);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

	@Test
	public void not_fail_if_the_any_output_is_zero () {

		final int input1 = 1;
		final int output1 = 1;
		final Hypothesis hypothesis1 = new Hypothesis(Arrays.asList(input1), output1);
		final Hypothesis hypothesis2 = new Hypothesis(Arrays.asList(input1), 0);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1, hypothesis2);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

}
