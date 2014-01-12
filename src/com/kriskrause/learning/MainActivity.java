package com.kriskrause.learning;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
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
import android.os.AsyncTask;

public class MainActivity extends Activity implements OnClickListener, ICallbackListener, TextToSpeech.OnInitListener
{
    public static final String TAG = "LEARNING";

    private static final int MaxCharSize = 500;
    private TextView _txtChar;
    private int _mode = 0;
    private SharedPreferences _prefs;
    private TextToSpeech _speech;
    private int _seqIndex = -1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	_prefs = PreferenceManager.getDefaultSharedPreferences(this);

	_speech = new TextToSpeech(this, this);

	_txtChar = (TextView) findViewById(R.id.txt_character);
        _txtChar.setOnClickListener(this);

	// Disable tap click sound.
	_txtChar.setSoundEffectsEnabled(false);
    }

   @Override
   public void onResume() {
	super.onResume();

	// TODO: Apply "fix" for Kindle Fire.

	// Apply setting changes for the action menu.
	invalidateOptionsMenu();
   }

    @Override public void onDestroy() {
	if (_speech != null) {
		_speech.stop();
		_speech.shutdown();
	}
	super.onDestroy();
    }

    @Override
    public void onInit(int status) {
       Context context = getApplicationContext();
       Toast toast;
       String msg;

       if (status == TextToSpeech.SUCCESS) {
	  int result = _speech.setLanguage(Locale.US);

	  if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	     msg = "Language not supported ...";
	  } else {
	     msg = "Speech ready ...";
	  }
       } else {
	  msg = "Speech init failed ...";
       }

	toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
	toast.show();
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
	boolean clickSound = new Boolean(_prefs.getBoolean("enable_speech", true));

	menu.getItem(0).setEnabled(clickSound);
	invalidateOptionsMenu();

       return super.onPrepareOptionsMenu(menu);
    }

    private void setMode(int mode) {
	_mode = mode;
	_seqIndex = -1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	boolean retVal = false;
	boolean shouldToast = true;

    	// Handle presses on the action bar items
        switch (item.getItemId()) {
		case R.id.action_letters:
			setMode(0); retVal = true; break;
		case R.id.action_1st100:
			setMode(1); retVal = true; break;
		case R.id.action_2nd100:
			setMode(2); retVal = true; break;
		case R.id.action_3rd100:
			setMode(3); retVal = true; break;
		case R.id.action_4th100:
			setMode(4); retVal = true; break;
		case R.id.action_5th100:
			setMode(5); retVal = true; break;
		case R.id.action_6th100:
			setMode(6); retVal = true; break;
		case R.id.action_7th100:
			setMode(7); retVal = true; break;
		case R.id.action_8th100:
			setMode(8); retVal = true; break;
		case R.id.action_9th100:
			setMode(9); retVal = true; break;
		case R.id.action_10th100:
			setMode(10); retVal = true; break;
        	case R.id.action_about:
			openAbout();
			retVal = true;
			shouldToast = false;
			break;
                case R.id.action_settings:
			openSettings();
			retVal = true;
			shouldToast = false;
			break;
		case R.id.action_play:
			retVal = true;
			shouldToast = false;

			// TODO: Is this good enough?  Is there a status?
			if (_speech != null) {
	  			_speech.speak(_txtChar.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
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
   	String[] chars;

	if (_mode == 1) {
		chars = Data.First100;
	} else if (_mode == 2) {
		chars = Data.Second100;
	} else if (_mode == 3) {
		chars = Data.Third100;
	} else if (_mode == 4) {
		chars = Data.Fourth100;
	} else if (_mode == 5) {
		chars = Data.Fifth100;
	} else if (_mode == 6) {
		chars = Data.Sixth100;
	} else if (_mode == 7) {
		chars = Data.Seventh100;
	} else if (_mode == 8) {
		chars = Data.Eigth100;
	} else if (_mode == 9) {
		chars = Data.Ninth100;
	} else if (_mode == 10) {
		chars = Data.Tenth100;
	} else {
		// _mode 0
		chars = Data.Letters;
	}

	if (selectionStyle.equals("random")) {
	   taskGetChar = new UpdateRandomCharTask();
	} else {
	   _seqIndex++;

	   if (_seqIndex >= chars.length) _seqIndex = 0;

	   taskGetChar = new UpdateSequentialCharTask(_seqIndex);
	}

	taskGetChar.setCallback(this);
	taskGetChar.execute(chars);
    }

   public void callback(String result) {
	int defaultWordSize = 85;
	int defaultLetterSize = 185;

	try {

	   int wordSize = Integer.parseInt(_prefs.getString("wordSize",  Integer.toString(defaultWordSize)));
	   int letterSize = Integer.parseInt(_prefs.getString("letterSize",  Integer.toString(defaultLetterSize)));

	   if (wordSize >= MaxCharSize) wordSize = defaultWordSize;
	   if (letterSize >= MaxCharSize) letterSize = defaultLetterSize;

	   _txtChar.setTextSize(TypedValue.COMPLEX_UNIT_SP, (_mode == 0) ? letterSize : wordSize);
	   _txtChar.setText(result);

	} catch (Exception ex) {
	   handleError("MainActivity::callback", ex);
	}
   }

   private void handleError(String message) {
   	handleError(message, null);
   }

   private void handleError(String message, Exception ex) {
	AlertDialog alert = new AlertDialog.Builder(this).create();
	alert.setTitle("Error");

	alert.setMessage(message + ((ex != null) ? " - " + ex.getMessage() : ""));
	alert.show();
   }

   private void openAbout() {
	try {
           Intent intent = new Intent(this, AboutActivity.class);

           startActivity(intent);
	} catch (Exception ex) {
	   handleError("MainActivity::openAbout", ex);
	}
   }

   private void openSettings() {
	try {
      	   Intent intent = new Intent(this, SettingsActivity.class);

	   startActivity(intent);
	} catch (Exception ex) {
	   handleError("MainActivity::openSettings");
	}
   }
}
