package com.example.spike.tdd.codegenerator;

import java.util.List;
import java.util.function.Function;

public class HypothesisSet {

	private final List<Hypothesis> hypotheses;

	public HypothesisSet (final List<Hypothesis> hypotheses) {

		this.hypotheses = hypotheses;
	}

	public Object findFormula () {
		return (Function) (o) -> (int)o;
	}
}
