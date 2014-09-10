package com.kriskrause.learning;

import android.os.AsyncTask;

public abstract class CharTask extends AsyncTask<DataItem, Void, DataItem> {
        private ICallbackListener _listener;

        abstract DataItem getNext(DataItem... params);

        public void setCallback(ICallbackListener listener) {
        	_listener = listener;
        }

        @Override
        protected DataItem doInBackground(DataItem... params) {
                return getNext(params);
        }

        @Override
        protected void onPostExecute(DataItem result) {
        	_listener.callbackTextChanged(result);
        }

        @Override
        protected void onPreExecute() {
                
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
}