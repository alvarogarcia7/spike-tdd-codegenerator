package com.example.spike.tdd.codegenerator.application;

import com.example.spike.tdd.codegenerator.operation.Difference;
import com.example.spike.tdd.codegenerator.operation.Division;
import com.example.spike.tdd.codegenerator.operation.Identity;
import com.example.spike.tdd.codegenerator.operation.Operation;
import com.example.spike.tdd.codegenerator.operation.OperationFinder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Applications {

	private final List<Application> applications;

	public Applications (final List<Application> applications) {

		this.applications = applications;
	}

	public Function findOperation () {

		final List<Operation> operations = Arrays.asList(new Difference(), new Division(), new Identity());
		return new OperationFinder(this, operations).findOperation();

	}

	public Application first () {
		return applications.get(0);
	}

	public List<Application> values () {
		return Collections.unmodifiableList(applications);
	}
}
