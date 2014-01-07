package com.kriskrause.learning;

import android.os.AsyncTask;

public class UpdateSequentialCharTask extends CharTask {
	private int _index = 0;

	public UpdateSequentialCharTask(int index) {
		_index = index;
	}

        @Override
        public String getNext(String... params) {
                return params[_index];
        }
}
