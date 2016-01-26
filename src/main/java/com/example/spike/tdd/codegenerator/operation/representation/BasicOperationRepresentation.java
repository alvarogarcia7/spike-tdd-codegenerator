package com.example.spike.tdd.codegenerator.operation.representation;

public class BasicOperationRepresentation extends OperationRepresentation {
	private final String description;

	protected BasicOperationRepresentation (final String description) {
		this.description = description;
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final BasicOperationRepresentation that = (BasicOperationRepresentation) o;

		if (description != null ? !simplify().equals(that.simplify()) : that.description != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		return description != null ? simplify().hashCode() : 0;
	}

	@Override
	public String toString () {
		return description;
	}

	private String simplify() {
		return this.description.replaceAll("\\s", "");
	}
}
