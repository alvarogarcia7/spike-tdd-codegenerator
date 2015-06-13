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
	public void detect_the_return_value () {
		assertThat(sut.getReturnValue().equals(4), Is.is(true));
	}

	@Test
	public void assert_on_the_production_method () {

		MatcherAssert.assertThat(sut.getMethodName(), Matchers.is("sum"));
	}

	@Test
	public void assert_on_the_production_parameters () {

		MatcherAssert.assertThat(sut.getParameters(), hasItems(2, 2));
	}

	@Test
	public void generate_the_production_method () {

		MatcherAssert.assertThat(sut.getProductionMethod(), Matchers.is("public Integer sum(Integer x, Integer y){ " +
				"return 0; }"));
	}

	@Test
	public void never_stop_generating_parameter_names () {

		sut = new Specification(
				"",
				"sum(2, 2,3,1,1,1,1,1,1,1,1,1,1,1,1)",
				"");

		MatcherAssert.assertThat(sut.getFormalParametersAsString(), Matchers.is(
				"Integer x, Integer y, Integer z, Integer a, Integer b, Integer c, Integer var6, Integer var7, Integer var8, " +
						"Integer var9, Integer var10, Integer var11, Integer var12, Integer var13, Integer var14"));
	}



	@Test
	public void generate_the_formal_parameters () {

		MatcherAssert.assertThat(sut.getFormalParametersAsString(), Matchers.is("Integer x, Integer y"));
	}

	private Class is (final Class returnType) {
		return returnType;
	}

	private void assertThatClassAssignableFrom (final Class actual, final Class expected) {
		assertThat(expected.isAssignableFrom(actual), Is.is(true));
	}

}
