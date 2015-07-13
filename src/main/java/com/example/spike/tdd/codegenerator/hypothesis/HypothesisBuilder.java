package com.example.spike.tdd.codegenerator.hypothesis;

import java.util.Arrays;

public class HypothesisBuilder {
	public static Hypothesis hypothesis (Object output, Object input) {
		return new Hypothesis(Arrays.asList(input), output);
	}
}
