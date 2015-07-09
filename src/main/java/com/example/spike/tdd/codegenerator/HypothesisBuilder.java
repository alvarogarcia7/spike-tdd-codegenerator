package com.example.spike.tdd.codegenerator;

import java.util.Arrays;

public class HypothesisBuilder {
	static Hypothesis hypothesis (Object output, Object input) {
		return new Hypothesis(Arrays.asList(input), output);
	}
}
