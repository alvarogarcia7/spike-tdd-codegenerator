package com.example.spike.tdd.codegenerator.application;

import java.util.List;
import java.util.StringJoiner;

public class Application {
	private final List<Object> input;
	private final Object output;

	public Application (final List<Object> input, final Object output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (!(o instanceof Application)) return false;

		final Application that = (Application) o;

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

	@Override
	public String toString () {
		final StringJoiner stringJoiner = new StringJoiner(", ");
		input.stream().forEach(current -> stringJoiner.add(String.valueOf(current)));
		return "f(" + stringJoiner.toString() +
				") = " + output;
	}

	public List<Object> getParameters () {
		return input;
	}

	public Object getOutput () {
		return output;
	}
}
