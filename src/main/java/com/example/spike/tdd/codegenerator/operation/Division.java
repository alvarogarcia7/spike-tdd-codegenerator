package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Division implements Operation {

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
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
