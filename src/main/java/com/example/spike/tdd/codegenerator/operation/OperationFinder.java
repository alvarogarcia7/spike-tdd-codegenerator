package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.application.Applications;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OperationFinder {
	private final Applications hypotheses;
	private final List<Operation> operations;

	public OperationFinder (final Applications hypotheses, final List<Operation> operations) {

		this.hypotheses = hypotheses;
		this.operations = operations;
	}

	public Function findOperation () {
		final List<Object> firstParameters = hypotheses.first().getParameters();
		assert (firstParameters.size() == 1);

		final List<Pair<Optional<Function>, Boolean>> doTheyMatch = operations.stream().map(this::verifyHypothesesOrDo).collect(Collectors.toList());

		final List<Pair<Optional<Function>, Boolean>> matching = doTheyMatch.stream().filter(x -> x.getValue() == true).collect(Collectors.toList());
		if (matching.size() == 1){
			final Optional<Function> key = matching.get(0).getKey();
			if (key.isPresent()) {
				return key.get();
			} else {
				throw new UnsupportedOperationException("Not yet ready");
			}
		} else if (matching .size() == 0){
			throw new UnsupportedOperationException("Not yet ready");
		} else {
			throw new UnsupportedOperationException("Ambiguous function");
		}
	}

	private Pair<Optional<Function>, Boolean> verifyHypothesesOrDo (final Operation operation) {
		Optional<Function> f;
		boolean matches = true;
		try {
			f = operation.find(hypotheses.values());
		} catch (Exception e) {
			f = Operation.NO_FUNCTION;
		}
		for (Application current : hypotheses.values()) {
			matches &= !notMatchesHypothesis(f, current);
		}
		return new Pair<>(f, matches);
	}


	private boolean notMatchesHypothesis (final Optional<Function> candidateFunction, final Application current) {
		return !candidateFunction.isPresent() || !candidateFunction.get().apply(current.getParameters().get(0)).equals(current.getOutput());
	}
}
