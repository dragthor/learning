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
		String[] chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				   "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				   "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"	
				 };

        	int idx = new Random().nextInt(chars.length);

		/* try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} */

                return chars[idx];
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
