package com.kriskrause.learning;

public class UpdateSequentialCharTask extends CharTask {
	private int _index = 0;

	public UpdateSequentialCharTask(int index) {
		_index = index;
	}

    @Override
    public DataItem getNext(DataItem... params) {
		// Guard against walking off end of array.
		if (_index >= params.length) _index = 0;

		if (_index < 0) _index = 0;

	    return params[_index];
	}
}