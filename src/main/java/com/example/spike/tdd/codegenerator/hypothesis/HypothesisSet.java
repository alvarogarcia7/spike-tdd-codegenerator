package com.example.spike.tdd.codegenerator.hypothesis;

import com.example.spike.tdd.codegenerator.operation.Difference;
import com.example.spike.tdd.codegenerator.operation.Division;
import com.example.spike.tdd.codegenerator.operation.Identity;
import com.example.spike.tdd.codegenerator.operation.Operation;
import com.example.spike.tdd.codegenerator.operation.OperationFinder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class HypothesisSet {

	private final List<Hypothesis> hypotheses;

	public HypothesisSet (final List<Hypothesis> hypotheses) {

		this.hypotheses = hypotheses;
	}

	public Function findOperation () {

		final List<Operation> operations = Arrays.asList(new Difference(), new Division(), new Identity());
		return new OperationFinder(this, operations).findOperation();

	}

	public Hypothesis first () {
		return hypotheses.get(0);
	}

	public List<Hypothesis> values () {
		return Collections.unmodifiableList(hypotheses);
	}
}
