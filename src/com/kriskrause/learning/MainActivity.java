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

public class MainActivity extends Activity implements OnClickListener, ICallbackListener
{
    private TextView _txtChar;
    private int _mode = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	_txtChar = (TextView) findViewById(R.id.txt_character);
        _txtChar.setOnClickListener(this);
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

    	// Handle presses on the action bar items
        switch (item.getItemId()) {
		case R.id.action_letters:
			_mode = 1;
			return true;
		case R.id.action_1st100:
			_mode = 2;
			return true;
		case R.id.action_2nd100:
			_mode = 3;
			return true;
		case R.id.action_3rd100:
			_mode = 4;
			return true;
        	case R.id.action_about:
			 openAbout();
                         return true;
                case R.id.action_settings:
			 openSettings();
                         return true;
                default:
                         return super.onOptionsItemSelected(item);
         }
    }

    @Override
    public void onClick(View v) {
	v.playSoundEffect(SoundEffectConstants.CLICK);

	UpdateCharTask taskGetChar = new UpdateCharTask();

	taskGetChar.setCallback(this);

	if (_mode == 2) {
		taskGetChar.execute(Data.First100);
	} else if (_mode == 3) {
		taskGetChar.execute(Data.Second100);
	} else if (_mode == 4) {
		taskGetChar.execute(Data.Third100);
	} else {
		taskGetChar.execute(Data.Letters);
	}
    }

   public void callback(String result) {
	_txtChar.setText(result);
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
