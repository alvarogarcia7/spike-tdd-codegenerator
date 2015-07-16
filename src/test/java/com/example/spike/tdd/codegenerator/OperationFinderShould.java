package com.example.spike.tdd.codegenerator;

import com.example.spike.tdd.codegenerator.application.Application;
import com.example.spike.tdd.codegenerator.application.Applications;
import com.example.spike.tdd.codegenerator.operation.BaseExponentiation;
import com.example.spike.tdd.codegenerator.operation.PowerOf;
import com.example.spike.tdd.codegenerator.operation.Constant;
import com.example.spike.tdd.codegenerator.operation.Difference;
import com.example.spike.tdd.codegenerator.operation.Division;
import com.example.spike.tdd.codegenerator.operation.Operation;
import com.example.spike.tdd.codegenerator.operation.OperationFinder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.example.spike.tdd.codegenerator.application.ApplicationBuilder.aNew;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OperationFinderShould {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	private List<Operation> operations = asList(new Difference(), new Division(), new Constant());

	@Test
	public void find_the_constant_output () {

		findFormulaMatching(applicationsFor(2, 2, 3, 2));
	}

	@Test
	public void find_the_successor () {

		findFormulaMatching(applicationsFor(2, 3, 3, 4));
	}

	@Test
	public void find_the_constant () {
		findFormulaMatching(applicationsFor(1, 0, 2, 0));
	}

	@Test
	public void throw_an_exception_when_a_formula_is_not_found(){

		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(is("Not yet ready"));

		findFormulaMatching(applicationsFor(1, 1, 1, 2));
	}

	@Test
	public void find_the_division_by_two () {
		findFormulaMatching(applicationsFor(4, 2, 2, 1));
	}

	@Test
	public void throw_an_exception_when_the_applications_are_ambiguous(){

		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(is("Ambiguous function"));

		findFormulaMatching(applicationsFor(1, 1));
	}

	@Test
	public void verify_the_applications_against_all_the_operations () {

		final Operation operation1 = mock(Operation.class);
		doReturn(Operation.NO_FUNCTION).when(operation1).find(anyList());
		final Applications applications = applicationsFor(1, 1);

		try {
			new OperationFinder(applications, Arrays.asList(operation1, operation1)).findOperation();
		} catch (UnsupportedOperationException e) {
			// no exception
		}

		verify(operation1, times(2)).find(applications.values());
	}

	@Test
	public void find_the_square_formula () {
		findFormulaMatchingAndAssert(applicationsFor(1, 1, 2, 4, 3, 9, 4, 16, 5, 25), new PowerOf());
	}

	@Test
	public void find_the_cubic_formula () {
		findFormulaMatchingAndAssert(applicationsFor(2, 8, 3, 27), new PowerOf());
	}

	@Test
	public void find_exponentiation_to_one () {
		findFormulaMatchingAndAssert(applicationsFor(1, 1, 2, 2), new PowerOf());
	}

	@Test
	public void find_exponentiation_to_zero () {
		findFormulaMatchingAndAssert(applicationsFor(1, 1, 2, 1), new PowerOf());
	}

	@Test
	public void find_exponentiation_to_10 () {
		findFormulaMatchingAndAssert(applicationsFor(1, 1, 2, 1024, 3, 59049), new PowerOf());
	}

	@Test
	public void find_base_two_exponentiation () {
		findFormulaMatchingAndAssert(applicationsFor(0, 1, 1, 2, 2, 4), new BaseExponentiation());
	}

	@Test
	public void find_base_three_exponentiation () {
		findFormulaMatchingAndAssert(applicationsFor(0, 1, 1, 3, 2, 9), new BaseExponentiation());
	}

	private Applications applicationsFor (final int... inputsAndOutputs) {
		if(inputsAndOutputs.length % 2 != 0) {
			fail("every input should have an output");
		}

		final List<Application> applications = new ArrayList<>(inputsAndOutputs.length / 2);

		for (int i = 0; i < inputsAndOutputs.length; i+=2) {
			final Object input1 = inputsAndOutputs[i];
			final Object output1 = inputsAndOutputs[i+1];
			applications.add(aNew().with(asList(input1), output1).build());
		}

		return getApplications(applications);
	}

	private Applications getApplications (final List<Application> applications) {
		return new Applications(applications);
	}

	private OperationFinder sutWith (final Applications applications, final List<Operation> operations) {
		return new OperationFinder(applications, operations);
	}

	private Function findFormulaMatching (final Applications applications) {
		return findFormulaMatching(applications, operations);
	}

	private Function findFormulaMatching (final Applications applications, final List<Operation> operations) {
		final Function formula = sutWith(applications, operations).findOperation();

		for (Application application : applications.values()) {
			assertThat(formula.apply(application.getParameters().get(0)), is(application.getOutput()));
		}

		return formula;
	}

	private Function findFormulaMatchingAndAssert (final Applications applications, final Operation operation) {
		return findFormulaMatching(applications, Arrays.asList(operation));
	}

}
