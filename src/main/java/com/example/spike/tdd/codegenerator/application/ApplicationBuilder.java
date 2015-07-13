package com.example.spike.tdd.codegenerator.application;

import javafx.util.Builder;

import java.util.List;

public class ApplicationBuilder implements Builder<Application> {
	private List<Object> objects;
	private Object output;

	public ApplicationBuilder with (final List<Object> objects, final Object output) {
		this.objects = objects;
		this.output = output;
		return this;
	}

	public static ApplicationBuilder aNew () {
		return new ApplicationBuilder();
	}

	@Override
	public Application build () {
		return new Application(objects, output);
	}

}
