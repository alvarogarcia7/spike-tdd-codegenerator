package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Addition / Subtraction operation
 *
 * Does not find the identity element (0)
 */
public class AdditionSubtraction extends SingleIntOperation {
	@Override
	public Optional<Function> find (List<Application> hypotheses) {
		final int input = firstInput(hypotheses);
		final int output = getOutput(hypotheses);

		final int increment = output - input;
		if (increment == 0) {
			return NO_FUNCTION;
		}

		return Optional.of((Function) (o) -> (int) o + increment);
	}
}
