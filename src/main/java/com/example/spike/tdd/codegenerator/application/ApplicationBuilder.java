package com.example.spike.tdd.codegenerator.application;

import java.util.Arrays;

public class ApplicationBuilder {
	public static Application application (Object output, Object input) {
		return new Application(Arrays.asList(input), output);
	}
}
