package com.kriskrause.learning;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.os.Bundle;
import java.util.Random;
import android.util.Log;

public class MainActivity extends Activity implements OnTouchListener
{
    private String[] _letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
    private TextView _txtChar;

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
    public boolean onTouch(View v, MotionEvent event) {
	Log.d("LEARNING", "Touched.");
	//Toast.makeText(this,"onTouch",Toast.LENGTH_LONG).show();

    	int idx = new Random().nextInt(_letters.length);

	_txtChar.setText(_letters[idx]);
	return true;
    }
}
