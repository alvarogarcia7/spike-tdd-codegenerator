package com.example.spike.tdd.codegenerator.operation;

public class BaseExponentiation extends SearchSingleIntOperation {

	@Override
	protected int apply (final int input, final int candidate) {
		return (int) Math.pow(candidate, input);
	}

	public OperationRepresentation representation() {
		return new OperationRepresentation("f(x) = k^x");
	}
}
