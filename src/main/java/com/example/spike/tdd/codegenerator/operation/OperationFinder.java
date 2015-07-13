package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.application.Applications;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class OperationFinder {
	private final Applications hypotheses;
	private final List<Operation> operations;

	public OperationFinder (final Applications hypotheses, final List<Operation> operations) {

		this.hypotheses = hypotheses;
		this.operations = operations;
	}

	public Function findOperation () {
		final Application firstApplication = hypotheses.first();
		final List<Object> firstParameters = firstApplication.getParameters();
		assert (firstParameters.size() == 1);


		Optional<Function> candidateOperation = Optional.empty();
		for (Operation operation : operations) {
			candidateOperation = verifyHypothesesOrDo(candidateOperation, () -> operation.find(hypotheses.values()));
		}

		if (candidateOperation.isPresent()) {
			return candidateOperation.get();
		} else {
			throw new UnsupportedOperationException("Not yet ready");
		}
	}

	private Optional<Function> verifyHypothesesOrDo (Optional<Function> candidateFunction, final Callable<Optional<Function>> runnable) {
		for (Application current : hypotheses.values()) {
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


	private boolean notMatchesHypothesis (final Optional<Function> candidateFunction, final Application current) {
		return !candidateFunction.isPresent() || !candidateFunction.get().apply(current.getParameters().get(0)).equals(current.getOutput());
	}
}
