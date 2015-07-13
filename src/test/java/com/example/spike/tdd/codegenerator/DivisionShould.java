package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.operation.Division;
import com.example.spike.tdd.codegenerator.hypothesis.Hypothesis;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.spike.tdd.codegenerator.hypothesis.HypothesisBuilder.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DivisionShould {

	@Test
	public void not_fail_if_the_first_output_is_zero () {

		final Hypothesis hypothesis1 = hypothesis(0, 1);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

	@Test
	public void not_fail_if_the_any_output_is_zero () {

		final int input1 = 1;
		final int output1 = 1;
		final Hypothesis hypothesis1 = hypothesis(output1, input1);
		final Hypothesis hypothesis2 = hypothesis(0, input1);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1, hypothesis2);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

}
