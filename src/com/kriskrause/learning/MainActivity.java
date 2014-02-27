package com.kriskrause.learning;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.view.SoundEffectConstants;
import android.util.TypedValue;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.app.AlertDialog;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
import android.content.res.Resources;

public class MainActivity extends Activity implements OnClickListener, ICallbackListener, TextToSpeech.OnInitListener
{
    public static final String TAG = "LEARNING";

    private static final String LAST_DISPLAY = "LAST_DISPLAY";
    private static final String LAST_DISPLAY_CLUE = "LAST_DISPLAY_CLUE";
    private static final String LAST_MODE = "LAST_MODE";
    private static final String LAST_SEQUENCE_POS = "LAST_SEQUENCE_POS";

    private static final int MaxCharSize = 500;
    private TextView _txtChar;
    private TextView _txtClue;
    private int _mode = 0;
    private SharedPreferences _prefs;
    private TextToSpeech _speech;
    private int _seqIndex = -1;
    private int _speechStatus = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
  
		_prefs = PreferenceManager.getDefaultSharedPreferences(this);

		_speech = new TextToSpeech(this, this);

		getTextChar().setOnClickListener(this);
        getClueTextChar().setOnClickListener(this);

		// Disable tap click sound.
		getTextChar().setSoundEffectsEnabled(false);
        getClueTextChar().setSoundEffectsEnabled(false);

		if (savedInstanceState != null) {
			_mode = savedInstanceState.getInt(LAST_MODE);
			_seqIndex = savedInstanceState.getInt(LAST_SEQUENCE_POS);

			DataItem item = new DataItem(
                    savedInstanceState.getString(LAST_DISPLAY),
                    savedInstanceState.getString(LAST_DISPLAY_CLUE));

			callbackTextChanged(item);
		}
    }

	@Override
	public void onResume() {
		super.onResume();

		// Apply setting changes for the action menu.
		invalidateOptionsMenu();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(LAST_DISPLAY, getTextChar().getText().toString());
        outState.putString(LAST_DISPLAY_CLUE, getClueTextChar().getText().toString());
		outState.putInt(LAST_MODE, _mode);
		outState.putInt(LAST_SEQUENCE_POS, _seqIndex);
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
    public void onInit(int status) {
		String msg;
		Resources res = getResources();
		boolean playSound = new Boolean(_prefs.getBoolean("enable_speech", true));

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

		Log.i(MainActivity.TAG, msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu items for use in the action bar
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main_activity_actions, menu);

         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
		boolean playSound = new Boolean(_prefs.getBoolean("enable_speech", true));

		MenuItem play = menu.findItem(R.id.action_play);
		play.setEnabled(playSound);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		boolean retVal = false;
		boolean shouldToast = true;

		// Handle presses on the action bar item  
		switch (item.getItemId()) {
			case R.id.action_letters:
				setModeAndGetNextChar(0); retVal = true; break;
			case R.id.action_1st100:
				setModeAndGetNextChar(1); retVal = true; break;
			case R.id.action_2nd100:
				setModeAndGetNextChar(2); retVal = true; break;
			case R.id.action_3rd100:
				setModeAndGetNextChar(3); retVal = true; break;
			case R.id.action_4th100:
				setModeAndGetNextChar(4); retVal = true; break;
			case R.id.action_5th100:
				setModeAndGetNextChar(5); retVal = true; break;
			case R.id.action_6th100:
				setModeAndGetNextChar(6); retVal = true; break;
			case R.id.action_7th100:
				setModeAndGetNextChar(7); retVal = true; break;
			case R.id.action_8th100:
				setModeAndGetNextChar(8); retVal = true; break;
			case R.id.action_9th100:
				setModeAndGetNextChar(9); retVal = true; break;
			case R.id.action_10th100:
				setModeAndGetNextChar(10); retVal = true; break;
			case R.id.action_numbers:
				setModeAndGetNextChar(11); retVal = true; break;
            case R.id.action_numbers_mandarin:
                setModeAndGetNextChar(12); retVal = true; break;
            case R.id.action_1st_char_mandarin:
                setModeAndGetNextChar(13); retVal = true; break;
			case R.id.action_about:
				openIntent(AboutActivity.class);
				retVal = true;
				shouldToast = false;
				break;
			case R.id.action_settings:
				openIntent(SettingsActivity.class);
				retVal = true;
				shouldToast = false;
				break;
			case R.id.action_play:
				retVal = true;
				shouldToast = false;

				// Anything below zero is not good.
				if (_speech != null && _speechStatus > -1) {
					_speech.speak(getTextChar().getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
				}
				break;
			default:
				retVal = super.onOptionsItemSelected(item);
				shouldToast = false;
				break;
		}

		if (retVal && shouldToast) {
			Toast toast = Toast.makeText(this, item.getTitle() + " ...", Toast.LENGTH_SHORT);
			toast.show();
		}

		return retVal;
    }

    @Override
    public void onClick(View v) {
		String selectionStyle = _prefs.getString("selection_style", "random");
		CharTask taskGetChar;
	   	String[] chars = Data.DataArray[_mode];

		if (selectionStyle.equals("random")) {
		   taskGetChar = new UpdateRandomCharTask();
		} else {
		   _seqIndex++;

		   if (_seqIndex >= chars.length) _seqIndex = 0;

		   taskGetChar = new UpdateSequentialCharTask(_seqIndex);
		}

		taskGetChar.setCallback(this);

        if (_mode == 0 || _mode > 10) { // Letters, Numbers, Mandarin
            taskGetChar.execute(Data.DataValues.get(_mode).toArray(new DataItem[Data.DataValues.get(_mode).size()]));
        } else {
		    taskGetChar.execute(Data.convertDataItems(chars));
        }
    }

	public void callbackTextChanged(DataItem result) {
		int defaultWordSize = 75;
		int defaultLetterSize = 175;

		try {

			int wordSize = Integer.parseInt(_prefs.getString("wordSize",  Integer.toString(defaultWordSize)));
			int letterSize = Integer.parseInt(_prefs.getString("letterSize",  Integer.toString(defaultLetterSize)));

			if (wordSize >= MaxCharSize) wordSize = defaultWordSize;
			if (letterSize >= MaxCharSize) letterSize = defaultLetterSize;

			// Single letters or numbers vs. words
			getTextChar().setTextSize(TypedValue.COMPLEX_UNIT_SP, (_mode == 0 || _mode > 10) ? letterSize : wordSize);
			
			if (result != null) {
				getTextChar().setText(result.getSymbol());
                getClueTextChar().setText(result.getClue());
			}

		} catch (Exception ex) {
			handleError("MainActivity::callbackTextChanged", ex);
		}
	}

    private void setModeAndGetNextChar(int mode) {
		_mode = mode;
		_seqIndex = -1;

		onClick(getTextChar());
    }
	
	private void handleError(String message) {
		handleError(message, null);
	}

	private void handleError(String message, Exception ex) {
		Log.e(MainActivity.TAG, "exception", ex);

		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setTitle("Error");

		alert.setMessage(message + ((ex != null) ? " - " + ex.getMessage() : ""));
		alert.show();
	}

	private void openIntent(Class name) {
		try {
			Intent intent = new Intent(this, name);
			startActivity(intent);
		} catch (Exception ex) {
			handleError("MainActivity::openIntent", ex);
		}
	}

	private TextView getTextChar() {
    	if (_txtChar == null) {
    		_txtChar = (TextView) findViewById(R.id.txt_character);
    	}

    	return _txtChar;
    }

    private TextView getClueTextChar() {
        if (_txtClue == null) {
            _txtClue = (TextView) findViewById(R.id.txt_clue);
        }

        return _txtClue;
    }
}