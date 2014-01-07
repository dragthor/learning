package com.kriskrause.learning;

import android.os.AsyncTask;

public abstract class CharTask extends AsyncTask<String, Void, String> {
	private ICallbackListener _listener;

	abstract String getNext(String... params);

	public void setCallback(ICallbackListener listener) {
		_listener = listener;
	}

        @Override
        protected String doInBackground(String... params) {
                return getNext(params);
        }

        @Override
        protected void onPostExecute(String result) {
		_listener.callback(result);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
}
