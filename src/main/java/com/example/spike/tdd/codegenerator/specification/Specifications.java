package com.example.spike.tdd.codegenerator.specification;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Specifications {
	private List<Specification> specifications;

	public Specifications (final List<Specification> tests) {

		specifications = tests;
	}

	public List<Specification> getSpecifications () {
		return specifications;
	}

	public List<Application> getApplications () {

		return specifications.stream()
				.map(current -> current.getApplication())
				.collect(toList());
	}
}
