package com.example.spike.tdd.codegenerator;

class TextRemover {
	private String currentText;

	public TextRemover (final String initialText) {

		currentText = initialText;
	}

	public TextRemover remove (final String text) {
		currentText = currentText.replaceFirst(text, "");
		return this;
	}

	public String get () {
		return currentText;
	}
}
