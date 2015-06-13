package com.example.spike.tdd.codegenerator;



import com.sun.istack.internal.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

class Specification {
	private final String name;
	private final String productionCode;
	private final String testCode;

	public Specification (final String name, final String productionCode, final String testCode) {

		this.name = name;
		this.productionCode = productionCode;
		this.testCode = testCode;
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (!(o instanceof Specification)) return false;

		final Specification that = (Specification) o;

		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (productionCode != null ? !productionCode.equals(that.productionCode) : that.productionCode != null)
			return false;
		if (testCode != null ? !testCode.equals(that.testCode) : that.testCode != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (productionCode != null ? productionCode.hashCode() : 0);
		result = 31 * result + (testCode != null ? testCode.hashCode() : 0);
		return result;
	}

	@Override
	public String toString () {
		return "Specification{" +
				"name='" + name + '\'' +
				", productionCode='" + productionCode + '\'' +
				", testCode='" + testCode + '\'' +
				'}';
	}

	public Class getReturnType () {
		return getReturnValue().getClass();
	}

	public String getMethodName () {
		return methodOnly(productionCode);
	}

	private String methodOnly (final String code) {
		return code.split("\\(")[0];
	}

	public List<Object> getParameters () {
		return parametersOnly(productionCode);
	}

	private List<Object> parametersOnly (final String code) {
		final String allParameters = code.replaceFirst(".*\\(", "").replaceAll("\\)", "");
		final String[] parameters = allParameters.split(",");
		return toParameter(parameters);
	}

	private List<Object> toParameter (final String[] parameters) {
		return Arrays.asList(parameters).stream()
				.map(current -> current.trim())
				.map(current -> getValueFrom(current))
				.collect(Collectors.toList());
	}

	private Object getValueFrom (final String current) {
		try {
			return Integer.valueOf(current);
		} catch (Exception e) {
			throw new RuntimeException("This type '" + current + "' is not yet known");
		}
	}

	public Object getReturnValue () {
		return parametersOnly(testCode).get(0);
	}

	public String getProductionMethod () {
		return  MethodBuilder.aNew().withReturn(getReturnType()).withName(getMethodName()).withFormalParameters
				(getFormalParametersAsString()).withBody("return 0;").build();
	}

	private static class MethodBuilder implements Builder<String>{

		private Class returnType;
		private String methodName;
		private String formalParametersAsString;
		private String body;

		public static MethodBuilder aNew () {
			return new MethodBuilder();
		}

		public MethodBuilder withReturn (final Class returnType) {
			this.returnType = returnType;
			return this;
		}

		@Override
		public String build () {
			return "public " + returnType.getSimpleName()+" " + methodName + "("+formalParametersAsString+"){ " +
					body+" }";
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
	}


	public String getFormalParametersAsString () {
		final List<String> parameterTypes = getParameters().stream()
				.map(current -> current.getClass().getSimpleName())
				.collect(Collectors.toList());

		final String[] names = new String[]{"x", "y", "z", "a", "b", "c"};

		final List<String> parametersWithNames = new ArrayList<>();


		int i=0;
		for (String parameterType : parameterTypes) {
			parametersWithNames.add(parameterType+" "+names[i]);
			i++;
		}

		StringJoiner stringJoiner = new StringJoiner(", ");
		parametersWithNames.forEach(stringJoiner::add);

		return stringJoiner.toString();
	}

}
