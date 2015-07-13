package com.example.spike.tdd.codegenerator.application;

import java.util.Collections;
import java.util.List;

public class Applications {

	private final List<Application> applications;

	public Applications (final List<Application> applications) {

		this.applications = applications;
	}

	public Application first () {
		return applications.get(0);
	}

	public List<Application> values () {
		return Collections.unmodifiableList(applications);
	}
}
