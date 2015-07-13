package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.operation.Division;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.spike.tdd.codegenerator.application.ApplicationBuilder.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DivisionShould {

	@Test
	public void not_fail_if_the_first_output_is_zero () {

		final Application application1 = aNew().with(Arrays.asList((Object) 1), (Object) 0).build();
		final List<Application> hypotheses = Arrays.asList(application1);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

	@Test
	public void not_fail_if_the_any_output_is_zero () {

		final int input1 = 1;
		final int output1 = 1;
		final Application application1 = aNew().with(Arrays.asList((Object) input1), (Object) output1).build();
		final Application application2 = aNew().with(Arrays.asList((Object) input1), (Object) 0).build();
		final List<Application> hypotheses = Arrays.asList(application1, application2);

		assertThat(new Division().find(hypotheses), is(Optional.empty()));
	}

}
