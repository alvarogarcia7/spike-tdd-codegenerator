package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.specification.Specification;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
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
		assertThatClassAssignableFrom(sut.getReturnType(), isClass(Integer.class));
	}

	@Test
	public void detect_the_return_value () {
		assertThat(sut.getReturnValue().equals(4), Is.is(true));
	}

	@Test
	public void assert_on_the_production_method () {

		assertThat(sut.getMethodName(), is("sum"));
	}

	@Test
	public void assert_on_the_production_parameters () {

		assertThat(sut.getApplication().getParameters(), hasItems(2, 2));
	}

	@Test
	public void generate_the_production_method () {

		assertThat(sut.getProductionMethod(), is("public Integer sum(Integer x, Integer y){ " +
				"return 0; }"));
	}

	@Test
	public void never_stop_generating_parameter_names () {

		sut = new Specification(
				"",
				"sum(2, 2,3,1,1,1,1,1,1,1,1,1,1,1,1)",
				"");

		assertThat(sut.getFormalParametersAsString(), is(
				"Integer x, Integer y, Integer z, Integer var3," +
				" Integer var4, Integer var5, Integer var6, Integer var7," +
				" Integer var8, Integer var9, Integer var10, Integer var11," +
				" Integer var12, Integer var13, Integer var14"));
	}



	@Test
	public void generate_the_formal_parameters () {

		assertThat(sut.getFormalParametersAsString(), is("Integer x, Integer y"));
	}

	@Test
	public void generate_the_spec_hypothesis () {

		assertThat(sut.getApplication(), is(new Application(Arrays.asList(2, 2), 4)));
	}

	private Class isClass (final Class returnType) {
		return returnType;
	}

	private void assertThatClassAssignableFrom (final Class actual, final Class expected) {
		assertThat(expected.isAssignableFrom(actual), Is.is(true));
	}

}
