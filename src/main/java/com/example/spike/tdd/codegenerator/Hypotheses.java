package com.example.spike.tdd.codegenerator;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Hypotheses {
	private List<Specification> specifications;

	public Hypotheses (final List<Specification> tests) {

		specifications = tests;
	}

	public List<Specification> getSpecifications () {
		return specifications;
	}

	public List<Hypothesis> getHypotheses () {

		return specifications.stream()
				.map(current -> current.getHypothesis())
				.collect(toList());
	}
}
