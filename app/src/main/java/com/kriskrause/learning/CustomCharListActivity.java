package com.kriskrause.learning;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CustomCharListActivity extends Activity implements TextToSpeech.OnInitListener  {
    private Set<String> _reviewItems;
    private TextToSpeech _speech;
    private int _speechStatus = -1;
    private SharedPreferences _prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_chars);

        _prefs = PreferenceManager.getDefaultSharedPreferences(this);
        _speech = new TextToSpeech(this, this);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadReviewItems();

        final ListView listView = (ListView) findViewById(R.id.custom_chars_list_view);

        final ArrayList<String> list = new ArrayList<String>();
        for (String s : _reviewItems) {
            list.add(s);
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                boolean playSound = _prefs.getBoolean("enable_speech", true);

                if (item == null) return;
                if (playSound == false) return;

                if (_speech != null && _speechStatus > -1) {
                    _speech.speak(item, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                list.remove(item);
                adapter.notifyDataSetChanged();

                _reviewItems.remove(item);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
        String msg;
        Resources res = getResources();

        _speechStatus = status;

        if (status == TextToSpeech.SUCCESS) {
            int result = _speech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                msg = res.getString(R.string.language_unsupported);
            } else {
                msg = res.getString(R.string.speech_ready);
            }
        } else {
            msg = res.getString(R.string.speech_fail);
        }

        Log.i(LearningApplication.TAG, msg);
    }

    @Override
    public void onDestroy() {
        if (_speech != null) {
            _speech.stop();
            _speech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences settings = getSharedPreferences(LearningApplication.LAST_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putStringSet(LearningApplication.CURRENT_REVIEW, _reviewItems);

        editor.apply();
    }

    private void loadReviewItems() {
        SharedPreferences settings = getSharedPreferences(LearningApplication.LAST_PREFS, 0);

        if (settings == null) return;

        _reviewItems = settings.getStringSet(LearningApplication.CURRENT_REVIEW, null);

        if (_reviewItems == null) {
            _reviewItems = new HashSet<String>();
        }
    }
}
