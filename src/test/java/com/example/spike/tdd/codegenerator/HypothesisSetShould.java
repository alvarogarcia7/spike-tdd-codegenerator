package com.example.spike.tdd.codegenerator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HypothesisSetShould {

	@Test
	public void find_the_identity () {
		findFormulaAndAssertItsCorrectness(2, 2);
	}

	@Test
	public void find_the_successor () {
		findFormulaAndAssertItsCorrectness(2, 3);
	}

	@Test
	public void find_the_formula_with_several_hypotheses () {
		//multiplying by zero
		final int input1 = 1;
		final int output1 = 0;
		final int input2 = 2;
		final int output2 = 0;


		final Hypothesis hypothesis1 = new Hypothesis(Arrays.asList(input1), output1);
		final Hypothesis hypothesis2 = new Hypothesis(Arrays.asList(input2), output2);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1, hypothesis2);
		final HypothesisSet sut = new HypothesisSet(hypotheses);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(input1), is(output1));
		assertThat(formula.apply(input2), is(output2));

	}

	@Test
	public void find_the_division_by_the_output_is_the_output () {
		//this is not the division by two nor the square (or its inverse) operation
		final int input1 = 4;
		final int output1 = 2;
		final int input2 = 2;
		final int output2 = 1;


		final Hypothesis hypothesis1 = new Hypothesis(Arrays.asList(input1), output1);
		final Hypothesis hypothesis2 = new Hypothesis(Arrays.asList(input2), output2);
		final List<Hypothesis> hypotheses = Arrays.asList(hypothesis1, hypothesis2);
		final HypothesisSet sut = new HypothesisSet(hypotheses);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(input1), is(output1));
		assertThat(formula.apply(input2), is(output2));

	}

	private void findFormulaAndAssertItsCorrectness (final int input, final int output) {
		final List<Object> parameters = Arrays.asList(input);
		final List<Hypothesis> hypotheses = Arrays.asList(new Hypothesis(parameters, output));
		final HypothesisSet sut = new HypothesisSet(hypotheses);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(input), is(output));
	}

}
