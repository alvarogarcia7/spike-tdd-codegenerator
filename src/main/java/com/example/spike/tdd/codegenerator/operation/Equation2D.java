package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Equation2D extends SingleIntOperation {

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		final Function g = obtainLineFunctionMatching(hypotheses);

		final int a = firstInput(hypotheses);
		final int b = getOutput(hypotheses);

		if (g.apply(a).equals(b)) {
			return Optional.of(g);
		}
		return Optional.empty();
	}

	private Function obtainLineFunctionMatching (final List<Application> hypotheses) {
		final List<Integer> inputParameters = hypotheses.stream()
				.map(Application::getParameters).map(x -> (int) x.get(0))
				.collect(Collectors.toList());
		final List<Integer> results = hypotheses.stream()
				.map(x -> (int) x.getOutput())
				.collect(Collectors.toList());

		Integer startingPoint = obtainStartingPoint(inputParameters, results);
		final int inclination = obtainInclination(inputParameters, results);
		return buildLineFunction(startingPoint, inclination);
	}

	private int obtainInclination (final List<Integer> inputParameters, final List<Integer> results) {
		final int deltaY = results.get(1) - results.get(0);
		final int deltaX = inputParameters.get(1) - inputParameters.get(0);
		return deltaY / deltaX;
	}

	private Integer obtainStartingPoint (final List<Integer> inputParameters, final List<Integer> results) {
		if(!inputParameters.contains(0)){
			throw new RuntimeException("Parameters must include function at x = 0");
		}
		assert inputParameters.contains(0);

		Integer startingPoint = 0;
		for (int i = 0; i < inputParameters.size(); i++) {
			if (inputParameters.get(i) == 0) {
				startingPoint = results.get(i);
			}
		}
		return startingPoint;
	}

	private Function buildLineFunction (final Integer startingPoint, final int incline) {
		final Function f = (x) -> (int) x * incline;
		final Integer finalStartingPoint = startingPoint;
		return f.andThen((x) -> (int) x + finalStartingPoint);
	}
}
