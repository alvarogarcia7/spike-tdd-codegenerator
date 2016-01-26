package com.example.spike.tdd.codegenerator.operation.representation;

public class OperationRepresentation {
	public static OperationRepresentation oneVariable (String description) {
		return new BasicOperationRepresentation("f(x) = " + description);
	}

	public static OperationRepresentation oneVariable (String description, String... restrictions) {
		return new RestrictedOperationRepresentation("f(x) = " + description, restrictions);
	}
}
