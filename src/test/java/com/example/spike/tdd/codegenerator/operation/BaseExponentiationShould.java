package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.operation.representation.OperationRepresentation;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BaseExponentiationShould {

	@Test
	public void represent_the_operation () {

		assertThat(new BaseExponentiation().representation(), is(OperationRepresentation.oneVariable("k ^ x")));
	}

}
