package com.example.spike.tdd.codegenerator;

import java.util.List;
import java.util.function.Function;

public class HypothesisSet {

	private final List<Hypothesis> hypotheses;

	public HypothesisSet (final List<Hypothesis> hypotheses) {

		this.hypotheses = hypotheses;
	}

	public Object findFormula () {

		final Hypothesis firstHypothesis = hypotheses.get(0);
		final List<Object> firstParameters = firstHypothesis.getParameters();
		assert (firstParameters.size() == 1);

		final int input = (int)firstParameters.get(0);
		final int output = (int) firstHypothesis.getOutput();

		final int increment = output - input;

		return (Function) (o) -> (int) o + increment;
	}
}
