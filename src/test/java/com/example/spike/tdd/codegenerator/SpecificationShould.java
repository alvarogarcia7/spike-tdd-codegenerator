package com.example.spike.tdd.codegenerator;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItems;
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
		assertThatClassAssignableFrom(sut.getReturnType(), is(Integer.class));
	}

	@Test
	public void assert_on_the_production_method () {

		MatcherAssert.assertThat(sut.getMethodName(), Matchers.is("sum"));
	}

	@Test
	public void assert_on_the_production_parameters () {

		MatcherAssert.assertThat(sut.getParameters(), hasItems(2, 2));
	}

	private Class is (final Class returnType) {
		return returnType;
	}

	private void assertThatClassAssignableFrom (final Class actual, final Class expected) {
		assertThat(expected.isAssignableFrom(actual), Is.is(true));
	}

}
