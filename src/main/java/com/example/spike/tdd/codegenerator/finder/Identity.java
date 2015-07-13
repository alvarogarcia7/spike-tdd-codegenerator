package com.example.spike.tdd.codegenerator.finder;

import com.example.spike.tdd.codegenerator.Hypothesis;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Identity implements FunctionFinder {

	public Optional<Function> find (final List<Hypothesis> hypotheses) {
		final Object first = hypotheses.get(0).getOutput();

		for (Hypothesis current : hypotheses) {
			if(!current.getOutput().equals(first)){
				return Optional.empty();
			}
		}

		final Function function =  (o) -> first;
		return Optional.of(function);
	}
}
