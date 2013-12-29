package com.kriskrause.learning;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
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
    private String[] _preK = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                               "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                               "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" 
                              };

    private String[] _k = { "and", "is", "dog", "cat", "mom", "dad", "I", "love", "you" 
			  };
    
    private String[] _1st = { "what", "also", "there", "again", "busy", "very", "hug", "because", "he", "she", "it", "good",
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
		case R.id.action_prek:
			_mode = 1;
			return true;
		case R.id.action_k:
			_mode = 2;
			return true;
		case R.id.action_1st:
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
	//Log.d("LEARNING", "Touched.");
	//Toast.makeText(this,"onTouch",Toast.LENGTH_LONG).show();

	UpdateCharTask taskGetChar = new UpdateCharTask();

	taskGetChar.setCallback(this);

	if (_mode == 2) {
		taskGetChar.execute(_k);
	} else if (_mode == 3) {
		taskGetChar.execute(_1st);
	} else {
		taskGetChar.execute(_preK);
	}

	return true;
    }

   public void callback(String result) {
	_txtChar.setText(result);
   }
}
