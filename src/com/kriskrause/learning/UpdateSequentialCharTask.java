package com.kriskrause.learning;

import android.os.AsyncTask;

public class UpdateSequentialCharTask extends CharTask {
	private int _index = 0;

	public UpdateSequentialCharTask(int index) {
		_index = index;
	}

        @Override
        public String getNext(String... params) {
		if (_index >= params.length) {
			// Guard against walking off end of array.
			_index = 0;
		}

                return params[_index];
        }
}
