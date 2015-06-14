package com.example.spike.tdd.codegenerator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
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
		findFormulaAndAssertItsCorrectness(2, 3);
	}

	private void findFormulaAndAssertItsCorrectness (final int input, final int output) {
		final List<Object> parameters = Arrays.asList(input);
		final List<Hypothesis> hypotheses = Arrays.asList(new Hypothesis(parameters, output));
		sut = new HypothesisSet(hypotheses);

		final Function formula = (Function)sut.findFormula();

		assertThat(formula.apply(input), is(output));
	}

}
