package com.example.spike.tdd.codegenerator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AdditionShould {

	@Test
	public void add_two_numbers () {

		assertThat(sum(2, 2), is(4));
	}
}
