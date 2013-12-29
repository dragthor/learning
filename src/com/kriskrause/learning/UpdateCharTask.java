package com.kriskrause.learning;

import android.os.AsyncTask;
import java.util.Random;

public class UpdateCharTask extends AsyncTask<String, Void, String> {
	private ICallbackListener _listener;
	
	public void setCallback(ICallbackListener listener) {
		_listener = listener;
	}

        @Override
        protected String doInBackground(String... params) {
        	int idx = new Random().nextInt(params.length);

                return params[idx];
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
