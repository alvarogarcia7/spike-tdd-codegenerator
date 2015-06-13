package com.example.spike.tdd.codegenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestReader {

	public static final String TEST_ANNOTATION = "@Test";
	public static final String LITERAL_PARENTHESIS_AND_SEMICOLON_ENDING_LINE = "\\);$";
	public static final String LITERAL_COMMA_ENDING_LINE = ",$";
	public static final String LITERAL_QUALIFIERS_FOR_TEST_METHOD = "\\s*public\\s*void\\s*";
	public static final String LITERAL_PARENTHESIS_AND_BRACKET = "\\s*\\(\\)\\s*\\{";
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

		i= trimmedLines.indexOf(TEST_ANNOTATION);
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
		final String testCode = newTextRemover(trimmedLines.get(i)).remove
				(LITERAL_PARENTHESIS_AND_SEMICOLON_ENDING_LINE).get();
		nextLine();
		return testCode;
	}

	private String getProductionCode (final List<String> trimmedLines) {
		final String productionCode = newTextRemover(trimmedLines.get(i)).remove(LITERAL_COMMA_ENDING_LINE).get();
		nextLine();
		return productionCode;
	}

	private TextRemover newTextRemover (final String initialText) {
		return new TextRemover(initialText);
	}

	private String getSpecHeader (final List<String> trimmedLines) {
		 String methodHeader = trimmedLines.get(i);

		methodHeader = newTextRemover(methodHeader)
				.remove(LITERAL_QUALIFIERS_FOR_TEST_METHOD)
				.remove(LITERAL_PARENTHESIS_AND_BRACKET)
				.get();
		nextLine();
		return methodHeader;
	}

	private void nextLine () {
		i++;
	}

	private void skip (final String _) {
		nextLine();
	}

	private List<String> trim (final List<String> lines) {
		return lines.stream().map(current -> current.trim()).filter(current -> current.length() > 0).collect(Collectors
				.toList());
	}

	private class TextRemover {
		private String currentText;

		public TextRemover (final String initialText) {

			currentText = initialText;
		}

		public TextRemover remove (final String text) {
			currentText = currentText.replaceFirst(text, "");
			return this;
		}

		public String get () {
			return currentText;
		}
	}
}
