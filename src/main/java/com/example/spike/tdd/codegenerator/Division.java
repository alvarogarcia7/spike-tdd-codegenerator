package com.example.spike.tdd.codegenerator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Division implements FunctionFinder{

	public Optional<Function> find (final List<Hypothesis> hypotheses) {
		final int a = (int) hypotheses.get(0).getParameters().get(0);
		final int b = (int) hypotheses.get(0).getOutput();
		if(hypotheses.stream().filter(x -> x.getOutput().equals(0)).findAny().isPresent()){
			return Optional.empty();
		}
		final int divisor = a / b;
		Function f = (x) -> (int) x / divisor;
		return Optional.of(f);
	}
}
