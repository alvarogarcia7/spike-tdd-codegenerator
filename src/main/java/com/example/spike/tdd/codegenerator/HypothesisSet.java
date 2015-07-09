package com.example.spike.tdd.codegenerator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class HypothesisSet {

	private Difference difference = new Difference();

	private final List<Hypothesis> hypotheses;

	public HypothesisSet (final List<Hypothesis> hypotheses) {

		this.hypotheses = hypotheses;
	}

	public Object findFormula () {

		final Hypothesis firstHypothesis = hypotheses.get(0);
		final List<Object> firstParameters = firstHypothesis.getParameters();
		assert (firstParameters.size() == 1);

		Optional<Function> candidateFunction = difference.find(hypotheses);

		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				candidateFunction = getFunctionConstantResultForAllHypotheses(hypotheses);
			}
		}

		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				candidateFunction = getFunctionDivision(hypotheses);
			}
		}


		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				throw new UnsupportedOperationException("Not yet ready");
			}
		}

		return candidateFunction.get();
	}


	private Optional<Function> getFunctionDivision (final List<Hypothesis> hypotheses) {
		final int a = (int) hypotheses.get(0).getParameters().get(0);
		final int b = (int) hypotheses.get(0).getOutput();
		final int divisor = a / b;
		Function f = (x) -> (int) x / divisor;
		return Optional.of(f);
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
		return !candidateFunction.isPresent() || !candidateFunction.get().apply(current.getParameters().get(0)).equals(current.getOutput());
	}

}
