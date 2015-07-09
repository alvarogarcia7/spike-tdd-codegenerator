package com.example.spike.tdd.codegenerator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class HypothesisSet {

	private Difference difference = new Difference();

	private Division division = new Division();

	private ConstantResult constantResult = new ConstantResult();

	private final List<Hypothesis> hypotheses;

	public HypothesisSet (final List<Hypothesis> hypotheses) {

		this.hypotheses = hypotheses;
	}

	public Object findFormula () {

		final Hypothesis firstHypothesis = hypotheses.get(0);
		final List<Object> firstParameters = firstHypothesis.getParameters();
		assert (firstParameters.size() == 1);

		Optional<Function> candidateFunction = difference.find(hypotheses);

		candidateFunction = next1(candidateFunction);

		candidateFunction = next2(candidateFunction);

		fail(candidateFunction);

		return candidateFunction.get();
	}

	private void fail (final Optional<Function> candidateFunction) {
		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				throw new UnsupportedOperationException("Not yet ready");
			}
		}
	}

	private Optional<Function> next2 (Optional<Function> candidateFunction) {
		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				candidateFunction = division.find(hypotheses);
			}
		}
		return candidateFunction;
	}

	private Optional<Function> next1 (Optional<Function> candidateFunction) {
		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				candidateFunction = constantResult.find(hypotheses);
			}
		}
		return candidateFunction;
	}


	private boolean notMatchesHypothesis (final Optional<Function> candidateFunction, final Hypothesis current) {
		return !candidateFunction.isPresent() || !candidateFunction.get().apply(current.getParameters().get(0)).equals(current.getOutput());
	}

}
