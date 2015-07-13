package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Identity implements Operation {

	public Optional<Function> find (final List<Application> hypotheses) {
		final Object first = hypotheses.get(0).getOutput();

		for (Application current : hypotheses) {
			if(!current.getOutput().equals(first)){
				return Optional.empty();
			}
		}

		final Function function =  (o) -> first;
		return Optional.of(function);
	}
}
