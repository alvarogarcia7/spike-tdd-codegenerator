package com.example.spike.tdd.codegenerator.operation.representation;

public class Restriction {
	private final String representation;

	private Restriction (final String representation) {
		this.representation = representation;
	}

	public static Restriction aNew (final String representation) {
		return new Restriction(representation);
	}
}
