package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.application.Applications;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OperationFinder {
	private final Applications applications;
	private final List<Operation> operations;

	public OperationFinder (final Applications applications, final List<Operation> operations) {

		this.applications = applications;
		this.operations = operations;
	}

	public Function findOperation () {
		final List<Object> firstParameters = applications.first().getParameters();
		assert (firstParameters.size() == 1);

		final List<Pair<Optional<Function>, Boolean>> matching = getMatchingFunctions();

		if (matching.size() == 1) {
			final Optional<Function> key = matching.get(0).getKey();
			if (key.isPresent()) {
				return key.get();
			}
		} else if(matching.size() > 1){
			throw new UnsupportedOperationException("Ambiguous function");
		}

		throw new UnsupportedOperationException("Not yet ready");
	}

	private List<Pair<Optional<Function>, Boolean>> getMatchingFunctions () {
		final List<Pair<Optional<Function>, Boolean>> doTheyMatch = operations.stream().map(this::matches).collect(Collectors.toList());
		return doTheyMatch.stream().filter(x -> x.getValue() == true).collect(Collectors.toList());
	}

	private Pair<Optional<Function>, Boolean> matches (final Operation operation) {
		Optional<Function> function;
		boolean matches = true;
		final List<Application> applications = this.applications.values();
		try {
			function = operation.find(applications);
		} catch (Exception e) {
			function = Operation.NO_FUNCTION;
		}
		for (Application current : applications) {
			matches &= matchesHypothesis(function, current);
		}
		return new Pair<>(function, matches);
	}


	private boolean matchesHypothesis (final Optional<Function> candidateFunction, final Application current) {
		return candidateFunction.isPresent()
				&& candidateFunction.get().apply(current.getParameters().get(0)).equals
				(current.getOutput());
	}
}
