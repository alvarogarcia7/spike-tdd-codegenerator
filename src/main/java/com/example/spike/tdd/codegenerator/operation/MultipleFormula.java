package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MultipleFormula extends SingleIntOperation {
	private final Operation[] operations;

	public MultipleFormula (final Operation... operations) {
		this.operations = operations;
	}

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final int a = firstInput(hypotheses);
		final int b = getOutput(hypotheses);

		final Function f = (x) -> (int) x * 2;
		final Function g = f.andThen((x) -> (int) x + 1);

		if (g.apply(a).equals(b)) {
			return Optional.of(g);
		}
		return Optional.empty();
	}
}
