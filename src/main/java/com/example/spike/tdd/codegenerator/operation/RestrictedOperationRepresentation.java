package com.example.spike.tdd.codegenerator.operation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestrictedOperationRepresentation extends OperationRepresentation {
	private final List<Restriction> restrictions;

	public RestrictedOperationRepresentation (final String representation, final String[] restrictionRepresentations) {
		super(representation);
		this.restrictions = reify(restrictionRepresentations);
	}

	/**
	 * https://en.wikipedia.org/wiki/Reification_(computer_science)
	 */
	private List<Restriction> reify (final String[] restrictionRepresentations) {
		return Collections.unmodifiableList(Arrays.asList(restrictionRepresentations).stream().map(Restriction::aNew)
				.collect(Collectors.toList()));
	}
}
