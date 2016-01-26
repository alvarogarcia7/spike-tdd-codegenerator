package com.example.spike.tdd.codegenerator.operation;

public class OperationRepresentation {
	private final String description;

	protected OperationRepresentation (final String description) {
		this.description = description;
	}

	public static OperationRepresentation oneVariable (String description) {
		return new OperationRepresentation("f(x) = " + description);
	}

	public static OperationRepresentation oneVariable (String description, String... restrictions) {
		return new RestrictedOperationRepresentation("f(x) = " + description, restrictions);
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final OperationRepresentation that = (OperationRepresentation) o;

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
