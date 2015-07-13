package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.application.Applications;
import com.example.spike.tdd.codegenerator.operation.Difference;
import com.example.spike.tdd.codegenerator.operation.Division;
import com.example.spike.tdd.codegenerator.operation.Identity;
import com.example.spike.tdd.codegenerator.operation.Operation;
import com.example.spike.tdd.codegenerator.operation.OperationFinder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.function.Function;

import static com.example.spike.tdd.codegenerator.application.ApplicationBuilder.aNew;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OperationFinderShould {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	private List<Operation> operations = asList(new Difference(), new Division(), new Identity());

	@Test
	public void find_the_identity () {

		final Function formula = findFormulaMatching(applicationsFor(2, 2, 3, 2));

		assertThat(formula.apply(2), is(2));
		assertThat(formula.apply(3), is(2));
	}

	@Test
	public void find_the_successor () {

		final Function formula = findFormulaMatching(applicationsFor(2, 3, 3, 4));

		assertThat(formula.apply(2), is(3));
		assertThat(formula.apply(3), is(4));
	}

	@Test
	public void find_the_formula_with_several_hypotheses () {
		//multiplying by zero

		final Function formula = findFormulaMatching(applicationsFor(1, 0, 2, 0));

		assertThat(formula.apply(1), is(0));
		assertThat(formula.apply(2), is(0));

	}

	@Test
	public void throw_an_exception_when_a_formula_is_not_found(){

		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(is("Not yet ready"));

		findFormulaMatching(applicationsFor(1, 1, 1, 2));
	}

	@Test
	public void find_the_division_by_the_output_is_the_output () {
		//this is not the division by two nor the square (or its inverse) operation

		final Function formula = findFormulaMatching(applicationsFor(4, 2, 2, 1));

		assertThat(formula.apply(4), is(2));
		assertThat(formula.apply(2), is(1));

	}

	@Test
	public void throw_an_exception_when_the_applications_are_ambiguous(){

		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(is("Ambiguous function"));

		findFormulaMatching(applicationFor(1, 1));
	}

	private Applications applicationsFor (final int input1, final int output1, final int input2, final int output2) {
		return getApplications(asList(
				aNew().with(asList(input1), output1).build(),
				aNew().with(asList(input2), output2).build()));
	}

	private Applications applicationFor (final int input, final int output) {
		return getApplications(asList(aNew().with(asList(input), output).build()));
	}

	private Applications getApplications (final List<Application> applications) {
		return new Applications(
				applications);
	}

	private OperationFinder sutWith (final Applications applications) {
		return new OperationFinder(applications, operations);
	}

	private Function findFormulaMatching (final Applications applications) {
		return sutWith(applications).findOperation();
	}

}
