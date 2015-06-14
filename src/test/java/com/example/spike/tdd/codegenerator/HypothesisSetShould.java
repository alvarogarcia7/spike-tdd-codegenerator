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
		findFormulaAndAssertItsCorrectness(2, 2);
	}

	@Test
	public void find_the_successor () {
		findFormulaAndAssertItsCorrectness(3, 2);
	}

	private void findFormulaAndAssertItsCorrectness (final int output, final int input) {
		sut = new HypothesisSet(Arrays.asList(new Hypothesis(Arrays.asList(input), output)));

		final Object formula = sut.findFormula();

		assertThat(((Function) formula).apply(input), is(output));
	}

}
