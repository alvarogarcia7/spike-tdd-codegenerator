package com.example.spike.tdd.codegenerator.finder;

import com.example.spike.tdd.codegenerator.hypothesis.Hypothesis;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface FunctionFinder {
	public Optional<Function> find(List<Hypothesis> hypotheses);
}
