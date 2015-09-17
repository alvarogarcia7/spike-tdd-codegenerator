package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * The division operation.
 *
 * Limitations: does not find the divisor 0 nor divisor 1 (identity element)
 */
public class Division extends SingleIntOperation {

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final int a = firstInput(hypotheses);
		final int b = getOutput(hypotheses);
		if (hypotheses.stream().filter(x -> x.getOutput().equals(0)).findAny().isPresent()) {
			return NO_FUNCTION;
		}
		final int divisor = a / b;
		if (divisor == 0 || divisor == 1) {
			return NO_FUNCTION;
		}
		return Optional.of((Function) (x) -> (int) x / divisor);
	}
}
