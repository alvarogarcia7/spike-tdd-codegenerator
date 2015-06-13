package com.example.spike.tdd.codegenerator;

import com.sun.istack.internal.Builder;

class MethodBuilder implements Builder<String> {

	public static final String SPACE = " ";
	private Class returnType;
	private String methodName;
	private String formalParametersAsString;
	private String body;
	private String visibility = "public";

	public static MethodBuilder aNew () {
		return new MethodBuilder();
	}

	public MethodBuilder withReturn (final Class returnType) {
		this.returnType = returnType;
		return this;
	}

	public MethodBuilder withName (final String methodName) {
		this.methodName = methodName;
		return this;
	}

	public MethodBuilder withFormalParameters (final String formalParametersAsString) {
		this.formalParametersAsString = formalParametersAsString;
		return this;
	}

	public MethodBuilder withBody (final String body) {
		this.body = body;
		return this;
	}

	@Override
	public String build () {
		return visibility + SPACE + returnType.getSimpleName() + SPACE + methodName +
				putParenthesisAround(formalParametersAsString) + putBracketsAround(body);
	}

	private String putBracketsAround (final String body) {
		return "{ " + body + " }";
	}

	private String putParenthesisAround (final String text) {
		return "(" + text + ")";
	}
}
