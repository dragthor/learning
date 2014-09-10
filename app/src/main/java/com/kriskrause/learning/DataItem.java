package com.kriskrause.learning;

public class DataItem {
	private String _symbol;
	private String _clue;

	public DataItem(String symbol, String clue) {
		_symbol = symbol;
		_clue = clue;
	}

	public String getSymbol() {
		return _symbol;
	}

	public String getClue() {
		return _clue;
	}
}