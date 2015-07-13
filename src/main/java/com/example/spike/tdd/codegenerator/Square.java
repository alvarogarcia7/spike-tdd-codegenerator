package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.operation.Operation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Square extends SingleIntOperation {
	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final int input = firstInput(hypotheses);
		final int output = getOutput(hypotheses);

		if(squared(input) == output) {
			return Optional.of((x) -> squared((int) x));
		} else {
			return Operation.NO_FUNCTION;
		}
	}

	private int squared (final int input) {
		return (int)Math.pow(input, 2);
	}
}
