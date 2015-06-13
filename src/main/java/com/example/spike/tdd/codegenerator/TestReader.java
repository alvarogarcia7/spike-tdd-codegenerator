package com.example.spike.tdd.codegenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestReader {

	private int i;

	public List<Specification> read (Path path){
		try {
			final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
			List<Specification> tests = findTests(lines);
			assert tests.size() == 1;
			return tests;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Specification> findTests (final List<String> lines) {

		final List<String> trimmedLines = trim(lines);

		final int testAppearsAt = trimmedLines.indexOf("@Test");
		System.out.println(testAppearsAt);
		i=testAppearsAt;
		skip("test_annotation");
		final String methodHeader = getSpecHeader(trimmedLines);
		skip("assert");

		final String productionCode = getProductionCode(trimmedLines);
		final String testCode = getTestCode(trimmedLines);

		final List<Specification> tests = new ArrayList<>();
		tests.add(new Specification(methodHeader, productionCode, testCode));
		return tests;
	}

	private String getTestCode (final List<String> trimmedLines) {
		final String testCode = trimmedLines.get(i).replaceFirst("\\);$","");
		nextLine();
		return testCode;
	}

	private String getProductionCode (final List<String> trimmedLines) {
		final String productionCode = trimmedLines.get(i).replaceFirst(",$", "");
		nextLine();
		return productionCode;
	}

	private String getSpecHeader (final List<String> trimmedLines) {
		 String methodHeader = trimmedLines.get(i);
		methodHeader = methodHeader.replaceFirst("\\s*public\\s*void\\s*","").replaceFirst("\\s*\\(\\)\\s*\\{","");
		nextLine();
		return methodHeader;
	}

	private void nextLine () {
		i++;
	}

	private void skip (final String reason) {
		i++;
	}

	private List<String> trim (final List<String> lines) {
		return lines.stream().map(current -> current.trim()).filter(current -> current.length() > 0).collect(Collectors
				.toList());
	}

	static class Specification {
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
	}
}
