package com.example.spike.tdd.codegenerator;

import java.util.List;

public class Hypotheses {
	private List<Specification> specifications;

	public Hypotheses (final List<Specification> tests) {

		specifications = tests;
	}

	public List<Specification> getSpecifications () {
		return specifications;
	}

	public List<Hypothesis> getHypotheses () {
		return null;
	}
}
