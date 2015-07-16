package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.SingleIntOperation;
import com.example.spike.tdd.codegenerator.application.Application;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AnyExponentiation extends SingleIntOperation {
	@Override
	public Optional<Function> find (final List<Application> hypotheses) {
		return Optional.empty();
	}
}
