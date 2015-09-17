package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Difference extends SingleIntOperation {
	@Override
	public Optional<Function> find (List<Application> hypotheses) {
		final int input = firstInput(hypotheses);
		final int output = getOutput(hypotheses);

		final int increment = output - input;
		if (increment == 0) {
			return Optional.empty();
		}

		return Optional.of((Function) (o) -> (int) o + increment);
	}
}
