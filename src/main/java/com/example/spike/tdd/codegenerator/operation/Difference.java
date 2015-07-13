package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Difference implements Operation {
	@Override
	public Optional<Function> find (List<Application> hypotheses) {
		final int input = (int)hypotheses.get(0).getParameters().get(0);
		final int output = (int) hypotheses.get(0).getOutput();

		final int increment = output - input;

		final Function function = (o) -> (int) o + increment;
		return Optional.of(function);
	}
}
