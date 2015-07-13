package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.SingleIntOperation;
import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Constant extends SingleIntOperation{

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final Object first = getOutput(hypotheses);

		for (Application current : hypotheses) {
			if (!current.getOutput().equals(first)) {
				return Optional.empty();
			}
		}

		return Optional.of((Function) (o) -> first);
	}
}
