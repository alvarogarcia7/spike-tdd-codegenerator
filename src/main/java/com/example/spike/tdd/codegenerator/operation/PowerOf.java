package com.example.spike.tdd.codegenerator.operation;

public class PowerOf extends SearchSingleIntOperation {

	@Deprecated
	protected int apply (final int input, final int candidate) {
		return (int) Math.pow(input, candidate);
	}

}
