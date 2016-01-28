package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Multiplication extends SingleIntOperation {

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final int a = firstInput(hypotheses);
		final int b = getOutput(hypotheses);
		final int factor = b / a;
		if (factor == 0 || factor == 1) {
			return NO_FUNCTION;
		}
		return Optional.of((Function) (x) -> (int) x * factor);
	}
}
