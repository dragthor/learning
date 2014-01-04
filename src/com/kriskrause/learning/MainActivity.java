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

public class MainActivity extends Activity implements OnClickListener, ICallbackListener
{
    public static final String LogTag = "LEARNING";

    private TextView _txtChar;
    private int _mode = 1;
    private SharedPreferences _prefs;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	_prefs = PreferenceManager.getDefaultSharedPreferences(this);

	_txtChar = (TextView) findViewById(R.id.txt_character);
        _txtChar.setOnClickListener(this);

	setSound(_txtChar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu items for use in the action bar
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main_activity_actions, menu);
         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getApplicationContext();
	boolean retVal = false;
	boolean shouldToast = true;

    	// Handle presses on the action bar items
        switch (item.getItemId()) {
		case R.id.action_letters:
			_mode = 1; retVal = true; break;
		case R.id.action_1st100:
			_mode = 2; retVal = true; break;
		case R.id.action_2nd100:
			_mode = 3; retVal = true; break;
		case R.id.action_3rd100:
			_mode = 4; retVal = true; break;
		case R.id.action_4th100:
			_mode = 5; retVal = true; break;
		case R.id.action_5th100:
			_mode = 6; retVal = true; break;
		case R.id.action_6th100:
			_mode = 7; retVal = true; break;
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

   private void setSound(View v) {
	boolean clickSound = new Boolean(_prefs.getBoolean("enable_sound", true));
	
	v.setSoundEffectsEnabled(clickSound);
   }

    @Override
    public void onClick(View v) {
	setSound(v);

	UpdateRandomCharTask taskGetChar = new UpdateRandomCharTask();

	taskGetChar.setCallback(this);

	if (_mode == 2) {
		taskGetChar.execute(Data.First100);
	} else if (_mode == 3) {
		taskGetChar.execute(Data.Second100);
	} else if (_mode == 4) {
		taskGetChar.execute(Data.Third100);
	} else if (_mode == 5) {
		taskGetChar.execute(Data.Fourth100);
	} else if (_mode == 6) {
		taskGetChar.execute(Data.Fifth100);
	} else if (_mode == 7) {
		taskGetChar.execute(Data.Sixth100);
	} else {
		taskGetChar.execute(Data.Letters);
	}
    }

   public void callback(String result) {
	try {
	   int wordSize = Integer.parseInt(_prefs.getString("wordSize",  "85"));
	   int letterSize = Integer.parseInt(_prefs.getString("letterSize",  "185"));

	   if (_mode == 1) {
		   _txtChar.setTextSize(TypedValue.COMPLEX_UNIT_SP, letterSize);
	   } else {
		   _txtChar.setTextSize(TypedValue.COMPLEX_UNIT_SP, wordSize);
	   }

	   _txtChar.setText(result);
	} catch (Exception ex) {
	   handleError("MainActivity::callback", ex);
	}
   }

   private void handleError(String message, Exception ex) {
	AlertDialog alert = new AlertDialog.Builder(this).create();
	alert.setTitle("Error");
	alert.setMessage(message + " - " + ex.getMessage());
        alert.show();
   }

   private void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);

        startActivity(intent);
   }

   private void openSettings() {
      	Intent intent = new Intent(this, SettingsActivity.class);

	startActivity(intent);
   }
}
