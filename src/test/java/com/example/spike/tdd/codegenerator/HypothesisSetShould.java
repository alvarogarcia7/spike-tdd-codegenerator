package com.example.spike.tdd.codegenerator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.function.Function;

import static com.example.spike.tdd.codegenerator.HypothesisBuilder.hypothesis;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HypothesisSetShould {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void find_the_identity () {
		final int anyNumber = 2;
		final HypothesisSet sut = sutWith(anyNumber, anyNumber);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(anyNumber), is(anyNumber));
	}

	@Test
	public void find_the_successor () {
		final int input = 2;
		final int output = 3;
		final HypothesisSet sut = sutWith(input, output);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(input), is(output));
	}

	@Test
	public void find_the_formula_with_several_hypotheses () {
		//multiplying by zero
		final int input1 = 1;
		final int output1 = 0;
		final int input2 = 2;
		final int output2 = 0;

		final HypothesisSet sut = sutWith(input1, output1, input2, output2);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(input1), is(output1));
		assertThat(formula.apply(input2), is(output2));

	}

	@Test
	public void throw_an_exception_when_a_formula_is_not_found(){
		final HypothesisSet sut = sutWith(1, 1, 1, 2);

		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(is("Not yet ready"));

		sut.findFormula();
	}

	@Test
	public void find_the_division_by_the_output_is_the_output () {
		//this is not the division by two nor the square (or its inverse) operation
		final int input1 = 4;
		final int output1 = 2;
		final int input2 = 2;
		final int output2 = 1;


		final HypothesisSet sut = sutWith(input1, output1, input2, output2);

		final Function formula = sut.findFormula();

		assertThat(formula.apply(input1), is(output1));
		assertThat(formula.apply(input2), is(output2));

	}

	private HypothesisSet sutWith (final int input1, final int output1, final int input2, final int output2) {
		return new HypothesisSet(
				asList(
						hypothesis(output1, input1),
						hypothesis(output2, input2)));
	}

	private HypothesisSet sutWith (final int input, final int output) {
		final List<Object> parameters = asList(input);
		final List<Hypothesis> hypotheses = asList(new Hypothesis(parameters, output));
		return new HypothesisSet(hypotheses);
	}

}
