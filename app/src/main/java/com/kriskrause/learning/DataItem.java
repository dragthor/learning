package com.kriskrause.learning;

public class DataItem implements Comparable<DataItem> {
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

    @Override
    public int compareTo(DataItem item) {
        return this._symbol.compareTo(item.getSymbol());
    }
}