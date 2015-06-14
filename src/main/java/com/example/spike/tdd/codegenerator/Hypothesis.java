package com.example.spike.tdd.codegenerator;

import java.util.List;

public class Hypothesis {
	private final List<Object> input;
	private final Object output;

	public Hypothesis (final List<Object> input, final Object output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (!(o instanceof Hypothesis)) return false;

		final Hypothesis that = (Hypothesis) o;

		if (input != null ? !input.equals(that.input) : that.input != null) return false;
		if (output != null ? !output.equals(that.output) : that.output != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = input != null ? input.hashCode() : 0;
		result = 31 * result + (output != null ? output.hashCode() : 0);
		return result;
	}
}
