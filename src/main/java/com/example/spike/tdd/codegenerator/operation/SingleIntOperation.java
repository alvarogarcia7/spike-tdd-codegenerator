package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;

public abstract class SingleIntOperation implements Operation {
	protected int firstInput (final List<Application> hypotheses) {
		return (int)hypotheses.get(0).getParameters().get(0);
	}

	protected int getOutput (final List<Application> hypotheses) {
		return (int) hypotheses.get(0).getOutput();
	}
}
