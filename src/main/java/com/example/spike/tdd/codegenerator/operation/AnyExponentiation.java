package com.example.spike.tdd.codegenerator.operation;

import com.example.spike.tdd.codegenerator.application.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class AnyExponentiation extends SingleIntOperation {

	private List<Application> hypotheses;

	@Override
	public Optional<Function> find (final List<Application> hypotheses) {

		this.hypotheses = hypotheses;
		final Set<Integer> exponentCandidates = getCandidateExponents();

		final List<Integer> matchingCandidates = filterAllThoseMatch(exponentCandidates);

		if (matchingCandidates.size() == 1) {
			System.out.println(matchingCandidates);
			return Optional.of((x) -> powAsInt((int) x, matchingCandidates.get(0)));
		} else if (matchingCandidates.size() > 1) {
			System.out.println("Found more than one solution: AnyExponentiation, with exponents " + matchingCandidates);
		}

		return Optional.empty();
	}

	private List<Integer> filterAllThoseMatch (final Set<Integer> exponentCandidates) {
		final List<Integer> matchingCandidates = new ArrayList<>();
		for (Integer exponentCandidate : exponentCandidates) {


			boolean matches = true;
			for (int i = 0; i < hypotheses.size(); i++) {
				final int input = getInput(hypotheses, i);
				final int output = getOutput(hypotheses, i);

				if (powAsInt(input, exponentCandidate) != output) {
					matches = false;
				}
			}

			if (matches) {
				matchingCandidates.add(exponentCandidate);
			}
		}
		return matchingCandidates;
	}

	private Set<Integer> getCandidateExponents () {
		final Set<Integer> exponentCandidates = new HashSet<>();
		for (int i = 0; i < hypotheses.size(); i++) {
			final int input = getInput(hypotheses, i);
			final int output = getOutput(hypotheses, i);

			for (int j = 0; j < output; j++) {
				if (powAsInt(input, j) == output) {
					exponentCandidates.add(j);
				}
			}
		}
		return exponentCandidates;
	}

	private int powAsInt (final int input, final int j) {
		return (int) Math.pow(input, j);
	}

	protected int getInput (final List<Application> hypotheses, int position) {
		return (int) hypotheses.get(position).getParameters().get(0);
	}

	protected int getOutput (final List<Application> hypotheses, int position) {
		return (int) hypotheses.get(position).getOutput();
	}
}
