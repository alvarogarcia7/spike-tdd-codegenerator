package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.specification.Specification;
import com.example.spike.tdd.codegenerator.specification.Specifications;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Paths.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReaderShould {

	@Test
	public void read_sample_file_additionShould () throws IOException {

		final Path path = javaSpecFromTestResource("Addition");

		Specifications specs = new TestReader().read(path);

		assertThat(specs.getSpecifications(), hasItems(new Specification(
				"add_two_numbers",
				"sum(2, 2)",
				"is(4)")));
	}

	@Test
	public void read_two_tests_in_the_same_file () throws IOException {

		final Path path = javaSpecFromTestResource("TwoAdditions");

		Specifications specs = new TestReader().read(path);

		assertThat(specs.getSpecifications(), hasItems(

				new Specification(
					"add_two_numbers",
					"sum(2, 2)",
					"is(4)"),

				new Specification(
					"add_two_different_numbers",
					"sum(1, 1)",
					"is(2)")
				));
	}

	private Path javaSpecFromTestResource (final String filename) {
		return get("src/test/resources/" + filename + "Should.java");
	}
}
