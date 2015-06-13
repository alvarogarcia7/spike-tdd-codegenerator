package com.example.spike.tdd.codegenerator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SpecificationShould {

	private Specification sut;

	@Before
	public void setUp () {
		sut = new Specification(
				"add_two_numbers",
				"sum(2, 2)",
				"is(4)");
	}


	@Test
	public void detect_the_return_type_as_integer () {

		assertThat(sut.getReturnType(), instanceOf(Integer.class));
	}

}
