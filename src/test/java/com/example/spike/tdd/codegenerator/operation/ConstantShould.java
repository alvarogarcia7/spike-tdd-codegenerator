package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.operation.representation.OperationRepresentation;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConstantShould {

	@Test
	public void have_a_representation () {

		assertThat(new Constant().representation(), is(OperationRepresentation.oneVariable("k")));
	}

}
