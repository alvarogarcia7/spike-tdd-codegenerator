package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.operation.representation.OperationRepresentation;
import com.example.spike.tdd.codegenerator.operation.representation.Representable;

public class BaseExponentiation extends SearchSingleIntOperation implements Representable {

	@Override
	protected int apply (final int input, final int candidate) {
		return (int) Math.pow(candidate, input);
	}

	public OperationRepresentation representation() {
		return OperationRepresentation.oneVariable("k^x");
	}
}
