package com.example.spike.tdd.codegenerator.operation;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OperationRepresentationShould {

	@Test
	public void not_care_about_whitespace_in_the_representation () {
		assertThat(OperationRepresentation.oneVariable("x"), is(OperationRepresentation.oneVariable("  x  ")));
	}

}
