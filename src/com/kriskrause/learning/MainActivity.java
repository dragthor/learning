package com.kriskrause.learning;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;

public class MainActivity extends Activity implements OnTouchListener, ICallbackListener
{
    private TextView _txtChar;
    private int _mode = 1;
    private String[] _letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                               "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                               "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" 
                              };

    private String[] _1st100 = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", "his", "they", "I", "at", "be", "this", "have", "from" };
    
    private String[] _2nd100 = { "what", "also", "there", "again", "busy", "very", "hug", "because", "he", "she", "it", "good",
			      "bad"
		            };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	_txtChar = (TextView) findViewById(R.id.txt_character);
        _txtChar.setOnTouchListener(this);
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
        	case R.id.action_about:
                         return true;
                case R.id.action_settings:
                         return true;
                default:
                         return super.onOptionsItemSelected(item);
         }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
	UpdateCharTask taskGetChar = new UpdateCharTask();

	taskGetChar.setCallback(this);

	if (_mode == 2) {
		taskGetChar.execute(_1st100);
	} else if (_mode == 3) {
		taskGetChar.execute(_2nd100);
	} else {
		taskGetChar.execute(_letters);
	}

	return true;
    }

   public void callback(String result) {
	_txtChar.setText(result);
   }
}
