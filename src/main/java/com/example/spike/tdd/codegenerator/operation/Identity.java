package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Identity extends SingleIntOperation {

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {

		for (Application current : hypotheses) {
			if (!current.getParameters().get(0).equals(current.getOutput())) {
				return NO_FUNCTION;
			}
		}

		return Optional.of((Function) (o) -> o);
	}
}
