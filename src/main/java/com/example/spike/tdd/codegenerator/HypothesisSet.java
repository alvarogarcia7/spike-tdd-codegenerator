package com.example.spike.tdd.codegenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class HypothesisSet {

	private Difference difference = new Difference();

	private Division division = new Division();

	private ConstantResult constantResult = new ConstantResult();

	private final List<Hypothesis> hypotheses;

	public HypothesisSet (final List<Hypothesis> hypotheses) {

		this.hypotheses = hypotheses;
	}

	public Function findFormula () {

		final Hypothesis firstHypothesis = hypotheses.get(0);
		final List<Object> firstParameters = firstHypothesis.getParameters();
		assert (firstParameters.size() == 1);

		final List<FunctionFinder> functionFinders = Arrays.asList(difference, division, constantResult);

		Optional<Function> candidateFunction = Optional.empty();
		for (FunctionFinder functionFinder : functionFinders) {
			candidateFunction = verifyHypothesesOrDo(candidateFunction, () -> functionFinder.find(hypotheses));
		}

		verifyHypothesesOrDo(candidateFunction, () -> {
			throw new UnsupportedOperationException("Not yet ready");
		});

		return candidateFunction.get();
	}

	private Optional<Function> verifyHypothesesOrDo (Optional<Function> candidateFunction, final Callable<Optional<Function>> runnable) {
		for (Hypothesis current : hypotheses) {
			if (notMatchesHypothesis(candidateFunction, current)) {
				try {
					return runnable.call();
				} catch (Exception e) {
					return Optional.empty();
				}
			}
		}
		return candidateFunction;
	}


	private boolean notMatchesHypothesis (final Optional<Function> candidateFunction, final Hypothesis current) {
		return !candidateFunction.isPresent() || !candidateFunction.get().apply(current.getParameters().get(0)).equals(current.getOutput());
	}

}
