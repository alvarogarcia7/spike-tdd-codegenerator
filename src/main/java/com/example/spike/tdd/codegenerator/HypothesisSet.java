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

		Function candidateFunction = getFunctionBasedOnAddingDifference(firstHypothesis, firstParameters);

		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				candidateFunction = (o) -> 0;
			}
		}

		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				throw new UnsupportedOperationException("Not yet ready");
			}
		}

		return candidateFunction;
	}

	private boolean notMatchesHypothesis (final Function candidateFunction, final Hypothesis current) {
		return !candidateFunction.apply(current.getParameters().get(0)).equals(current.getOutput());
	}

	private Function getFunctionBasedOnAddingDifference (final Hypothesis firstHypothesis, final List<Object> firstParameters) {
		final int input = (int)firstParameters.get(0);
		final int output = (int) firstHypothesis.getOutput();

		final int increment = output - input;

		return (o) -> (int) o + increment;
	}
}
