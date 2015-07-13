package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.specification.Specification;
import com.example.spike.tdd.codegenerator.specification.Specifications;

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
	private int currentLine;

	public Specifications read (Path path){
		try {
			final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
			return new Specifications(findTests(lines));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Specification> findTests (final List<String> lines) {

		final List<String> trimmedLines = trimAndDeleteEmptyLines(lines);
		final List<Specification> tests = new ArrayList<>();


		while(isSpecificationAvailableInWhichLine(trimmedLines)) {
			final Specification specification = getSpecification(trimmedLines);
			tests.add(specification);
		}

		return tests;
	}

	private boolean isSpecificationAvailableInWhichLine (final List<String> trimmedLines) {

		int i;
		for(i=this.currentLine; i<trimmedLines.size(); i++){
			final String currentLine = trimmedLines.get(i);
			if(TEST_ANNOTATION.equals(currentLine)){
				this.currentLine = i;
				return true;
			}
		}
		return false;
	}

	private Specification getSpecification (final List<String> trimmedLines) {
		skip("test_annotation");
		final String methodHeader = getSpecHeader(trimmedLines);
		skip("assert");

		final String productionCode = getProductionCode(trimmedLines);
		final String testCode = getTestCode(trimmedLines);
		return new Specification(methodHeader, productionCode, testCode);
	}

	private String getTestCode (final List<String> trimmedLines) {
		final String testCode = newTextRemover(trimmedLines.get(currentLine))
				.remove(LITERAL_PARENTHESIS_AND_SEMICOLON_ENDING_LINE)
				.get();
		nextLine();
		return testCode;
	}

	private String getProductionCode (final List<String> trimmedLines) {
		final String productionCode = newTextRemover(trimmedLines.get(currentLine))
				.remove(LITERAL_COMMA_ENDING_LINE)
				.get();
		nextLine();
		return productionCode;
	}

	private TextRemover newTextRemover (final String initialText) {
		return new TextRemover(initialText);
	}

	private String getSpecHeader (final List<String> trimmedLines) {
		 String methodHeader = trimmedLines.get(currentLine);

		methodHeader = newTextRemover(methodHeader)
				.remove(LITERAL_QUALIFIERS_FOR_TEST_METHOD)
				.remove(LITERAL_PARENTHESIS_AND_BRACKET)
				.get();
		nextLine();
		return methodHeader;
	}

	private void nextLine () {
		currentLine++;
	}

	private void skip (final String _) {
		nextLine();
	}

	private List<String> trimAndDeleteEmptyLines (final List<String> lines) {
		return lines.stream()
				.map(current -> current.trim())
				.filter(current -> current.length() > 0)
				.collect(Collectors.toList());
	}

}
