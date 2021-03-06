package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface Operation {
	Optional<Function> NO_FUNCTION = Optional.empty();

	public Optional<Function> find(List<Application> hypotheses);
}
