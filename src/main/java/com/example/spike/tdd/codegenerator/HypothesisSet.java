package com.example.spike.tdd.codegenerator;

import java.util.List;
import java.util.Optional;
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

		Optional<Function> candidateFunction = Optional.of(getFunctionBasedOnAddingDifference(firstHypothesis, firstParameters));

		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				candidateFunction = getFunctionConstantResultForAllHypotheses(hypotheses);
			}
		}

		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				throw new UnsupportedOperationException("Not yet ready");
			}
		}

		return candidateFunction.get();
	}

	private Optional<Function> getFunctionConstantResultForAllHypotheses (final List<Hypothesis> hypotheses) {
		final Object first = hypotheses.get(0).getOutput();

		for (Hypothesis current : hypotheses) {
			if(!current.getOutput().equals(first)){
				return Optional.empty();
			}
		}

		final Function function =  (o) -> first;
		return Optional.of(function);
	}

	private boolean notMatchesHypothesis (final Optional<Function> candidateFunction, final Hypothesis current) {
		final boolean b = !candidateFunction.get().apply(current.getParameters().get(0)).equals(current.getOutput());
		return !candidateFunction.isPresent() ||b;
	}

	private Function getFunctionBasedOnAddingDifference (final Hypothesis firstHypothesis, final List<Object> firstParameters) {
		final int input = (int)firstParameters.get(0);
		final int output = (int) firstHypothesis.getOutput();

		final int increment = output - input;

		return (o) -> (int) o + increment;
	}
}
