package com.example.spike.tdd.codegenerator.finder;

import com.example.spike.tdd.codegenerator.hypothesis.Hypothesis;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Difference implements FunctionFinder{
	public Optional<Function> find (List<Hypothesis> hypotheses) {
		final int input = (int)hypotheses.get(0).getParameters().get(0);
		final int output = (int) hypotheses.get(0).getOutput();

		final int increment = output - input;

		final Function function = (o) -> (int) o + increment;
		return Optional.of(function);
	}
}
