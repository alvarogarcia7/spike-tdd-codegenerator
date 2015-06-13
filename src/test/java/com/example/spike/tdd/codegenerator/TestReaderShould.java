package com.example.spike.tdd.codegenerator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReaderShould {

	@Test
	public void read_sample_file_additionShould () throws IOException {

		final Path path = get("src/test/resources/AdditionShould.java");
		assertThat(path, is(not(nullValue())));

		final List<String> strings = Files.readAllLines(path);
		assertThat(strings.size(), is(not(0)));
//		strings.forEach(System.out::println);

		final List<TestReader.Specification> specs = new TestReader().read(path);

		assertThat(specs, hasItems(new TestReader.Specification("public void add_two_numbers () {", "sum(2, 2),", "is(4));")));
	}

}
