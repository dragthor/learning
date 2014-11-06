package com.kriskrause.learning;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

public class SettingsActivity extends PreferenceActivity implements OnPreferenceClickListener {

    private static final String SPEECH_SETTINGS_BUTTON_NAME = "button_tts_settings";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.preferences);

      if (getActionBar() != null) {
        getActionBar().setDisplayHomeAsUpEnabled(true);
      }

      Preference speechSettings = (Preference) findPreference(SPEECH_SETTINGS_BUTTON_NAME);
      speechSettings.setOnPreferenceClickListener(this);
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
    public boolean onPreferenceClick(Preference preference) {
      if (preference.getKey().equals(SPEECH_SETTINGS_BUTTON_NAME)) {
        openSpeechSettings();
      }
      return false;
    }

    private void openSpeechSettings() {
      try {
        Intent intent = new Intent();

        intent.setAction("com.android.settings.TTS_SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
      } catch (Exception ex) {
        Log.e(LearningApplication.TAG, "exception", ex);
      }
    }
}