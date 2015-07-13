package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.operation.Operation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Square implements Operation {
	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final int input = (int)hypotheses.get(0).getParameters().get(0);
		final int output = (int) hypotheses.get(0).getOutput();

		if((int)Math.pow(input, 2) == output) {
			return Optional.of((x) -> (int) Math.pow((int) x, 2));
		} else {
			return Operation.NO_FUNCTION;
		}
	}
}
