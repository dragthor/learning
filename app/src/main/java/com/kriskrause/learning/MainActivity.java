package com.kriskrause.learning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends Activity
        implements GestureDetector.OnGestureListener, ICallbackListener, TextToSpeech.OnInitListener {

    private static final String LAST_DISPLAY = "LAST_DISPLAY";
    private static final String LAST_DISPLAY_CLUE = "LAST_DISPLAY_CLUE";
    private static final String LAST_MODE = "LAST_MODE";
    private static final String LAST_MODE_DISPLAY = "LAST_MODE_DISPLAY";
    private static final String LAST_SEQUENCE_POS = "LAST_SEQUENCE_POS";

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private static final int MaxCharSize = 500;

    private TextView _txtChar;
    private TextView _txtClue;
    private int _mode = 0;
    private String _modeDisplay = LearningApplication.LAST_MODE_DISPLAY_DEFAULT;
    private SharedPreferences _prefs;
    private TextToSpeech _speech;
    private int _seqIndex = -1;
    private int _speechStatus = -1;
    private GestureDetector _detector;
    private Set<String> _reviewItems;
    private Toast _toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataItem item = null;

        _prefs = PreferenceManager.getDefaultSharedPreferences(this);

        _speech = new TextToSpeech(this, this);

        _detector = new GestureDetector(this, this);

        if (savedInstanceState != null) {
            _mode = savedInstanceState.getInt(LAST_MODE, 0);
            _seqIndex = savedInstanceState.getInt(LAST_SEQUENCE_POS, -1);
            _modeDisplay = savedInstanceState.getString(LAST_MODE_DISPLAY, LearningApplication.LAST_MODE_DISPLAY_DEFAULT);

            item = new DataItem(
                    savedInstanceState.getString(LAST_DISPLAY, LearningApplication.LAST_DISPLAY_DEFAULT),
                    savedInstanceState.getString(LAST_DISPLAY_CLUE, LearningApplication.LAST_DISPLAY_CLUE_DEFAULT));
        } else {
            // Attempt to get from shared preferences.
            SharedPreferences settings = getSharedPreferences(LearningApplication.LAST_PREFS, 0);

            if (settings != null) {
                _mode = settings.getInt(LAST_MODE, 0);
                _seqIndex = settings.getInt(LAST_SEQUENCE_POS, -1);
                _modeDisplay = settings.getString(LAST_MODE_DISPLAY, LearningApplication.LAST_MODE_DISPLAY_DEFAULT);

               item = new DataItem(
                        settings.getString(LAST_DISPLAY, LearningApplication.LAST_DISPLAY_DEFAULT),
                        settings.getString(LAST_DISPLAY_CLUE, LearningApplication.LAST_DISPLAY_CLUE_DEFAULT));
            }
        }

        callbackTextChanged(item);

        loadReviewItems();
    }

    @Override
    public void onStart() {
        super.onStart();

        loadReviewItems();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Apply setting changes for the action menu.
        invalidateOptionsMenu();
    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences settings = getSharedPreferences(LearningApplication.LAST_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt(LAST_MODE, _mode);
        editor.putInt(LAST_SEQUENCE_POS, _seqIndex);
        editor.putString(LAST_DISPLAY, getTextChar().getText().toString());
        editor.putString(LAST_DISPLAY_CLUE, getClueTextChar().getText().toString());
        editor.putString(LAST_MODE_DISPLAY, _modeDisplay);
        editor.putStringSet(LearningApplication.CURRENT_REVIEW, _reviewItems);

        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(LAST_DISPLAY, getTextChar().getText().toString());
        outState.putString(LAST_DISPLAY_CLUE, getClueTextChar().getText().toString());
        outState.putInt(LAST_MODE, _mode);
        outState.putInt(LAST_SEQUENCE_POS, _seqIndex);
        outState.putString(LAST_MODE_DISPLAY, _modeDisplay);
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
    public boolean onKeyDown(int keyCode, KeyEvent event){
        boolean handled = false;

        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_BUTTON_A:
                updateChars();
                handled = true;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_BUTTON_B:
                saveForReview();
                handled = true;
                break;
        }
        return handled || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        setSubTitle(_modeDisplay);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean playSound = _prefs.getBoolean("enable_speech", true);

        if (getActionBar() == null) {
            playSound = false;
        } else if (getActionBar().isShowing() == false) {
            playSound = false;
        }

        MenuItem play = menu.findItem(R.id.action_play);
        play.setEnabled(playSound);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean retVal = false;
        boolean shouldToast = true;

        // Handle presses on the action bar item.  I would like this to be
        // more elegant... as in.. have a convention... menu index = data index?
        switch (item.getItemId()) {
            case R.id.action_letters:
                setModeAndGetNextChar(0); retVal = true; break;
            case R.id.action_numbers:
                setModeAndGetNextChar(1); retVal = true; break;
            case R.id.action_1st100:
                setModeAndGetNextChar(2); retVal = true; break;
            case R.id.action_2nd100:
                setModeAndGetNextChar(3); retVal = true; break;
            case R.id.action_3rd100:
                setModeAndGetNextChar(4); retVal = true; break;
            case R.id.action_4th100:
                setModeAndGetNextChar(5); retVal = true; break;
            case R.id.action_5th100:
                setModeAndGetNextChar(6); retVal = true; break;
            case R.id.action_6th100:
                setModeAndGetNextChar(7); retVal = true; break;
            case R.id.action_7th100:
                setModeAndGetNextChar(8); retVal = true; break;
            case R.id.action_8th100:
                setModeAndGetNextChar(9); retVal = true; break;
            case R.id.action_9th100:
                setModeAndGetNextChar(10); retVal = true; break;
            case R.id.action_10th100:
                setModeAndGetNextChar(11); retVal = true; break;
            case R.id.action_reviewItems:
                setModeAndGetNextChar(12); retVal = true; break;
            case R.id.action_about:
                openIntent(AboutActivity.class);
                retVal = true;
                shouldToast = false;
                break;
            case R.id.action_help:
                openIntent(HelpActivity.class);
                retVal = true;
                shouldToast = false;
                break;
            case R.id.action_settings:
                openIntent(SettingsActivity.class);
                retVal = true;
                shouldToast = false;boolean enableClue = _prefs.getBoolean("enable_clue", true);
                break;
            case R.id.action_review:
                openIntent(CustomCharListActivity.class);
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
            setSubTitle(item.getTitle().toString());

            showToast(item.getTitle() + " ...");
        }

        return retVal;
    }

    private void updateChars() {
        String selectionStyle = _prefs.getString("selection_style", "random");
        CharTask taskGetChar;
        IData language;
        ArrayList<DataItem> data;

        try {
            // Change to factory eventually depending on multi-language support.
            language = new EnglishData();

            if (_mode == language.getReviewIndex()) {
                data = new ArrayList<DataItem>();

                if (_reviewItems != null) {
                    for (String s : _reviewItems) {
                        data.add(new DataItem(s, ""));
                    }

                    Collections.sort(data);
                }
            } else {
                data = language.getItems(_mode);
            }

            if (data == null || data.size() == 0) return;

            if (selectionStyle.equals("random")) {
                taskGetChar = new UpdateRandomCharTask();
            } else {
                _seqIndex++;

                if (_seqIndex >= data.size()) _seqIndex = 0;

                taskGetChar = new UpdateSequentialCharTask(_seqIndex);
            }

            taskGetChar.setCallback(this);

            taskGetChar.execute(data.toArray(new DataItem[data.size()]));
        } catch (Exception ex) {
            handleError("MainActivity::onClick", ex);
        }
    }

    public void callbackTextChanged(DataItem result) {
        int defaultWordSize = 75;
        int defaultLetterSize = 175;

        if (result == null) return;

        try {
            boolean enableClue = _prefs.getBoolean("enable_clue", true);
            int wordSize = Integer.parseInt(_prefs.getString("wordSize",  Integer.toString(defaultWordSize)));
            int letterSize = Integer.parseInt(_prefs.getString("letterSize",  Integer.toString(defaultLetterSize)));

            if (wordSize >= MaxCharSize) wordSize = defaultWordSize;
            if (letterSize >= MaxCharSize) letterSize = defaultLetterSize;

            // Single letters or numbers vs. words
            // Auto resize
            // getTextChar().setTextSize(TypedValue.COMPLEX_UNIT_SP, (_mode == 0 || _mode == 1) ? letterSize : wordSize);

            getTextChar().setText(result.getSymbol());
            getClueTextChar().setText( enableClue ? result.getClue() : "" );
        } catch (Exception ex) {
            handleError("MainActivity::callbackTextChanged", ex);
        }
    }

    private void setModeAndGetNextChar(int mode) {
        _mode = mode;
        _seqIndex = -1;

        updateChars();
    }

    private void handleError(String message, Exception ex) {
        Log.e(LearningApplication.TAG, "exception", ex);

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

    private void setSubTitle(String menuText) {
        try {
            if (menuText == null || menuText.length() == 0) {
                return;
            }

            if (getActionBar() == null) return;
            if (!getActionBar().isShowing()) return;

            getActionBar().setSubtitle(menuText);

            _modeDisplay = menuText;
        } catch (Exception ex) {
            handleError("MainActivity::setSubTitle", ex);
        }
    }

    private void loadReviewItems() {
        SharedPreferences settings = getSharedPreferences(LearningApplication.LAST_PREFS, 0);

        if (settings == null) return;

        _reviewItems = settings.getStringSet(LearningApplication.CURRENT_REVIEW, null);

        if (_reviewItems == null) {
            _reviewItems = new HashSet<String>();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this._detector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return true;
    }

    private void saveForReview() {
        String currentChar = getTextChar().getText().toString();

        _reviewItems.add(currentChar);

        showToast(currentChar  + " marked for review.");
    }

    private void showToast(String msg) {
        if (msg == null) return;
        if (msg.length() == 0) return;

        if (_toast == null) {
            _toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }

        _toast.setText(msg);
        _toast.show();
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        saveForReview();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = motionEvent2.getY() - motionEvent.getY();
            float diffX = motionEvent2.getX() - motionEvent.getX();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        // Swiped right.
                        updateChars();
                    } else {
                        // Swiped left.
                    }
                }
                result = true;
            }

            result = true;

        } catch (Exception ex) {
            handleError("MainActivity::onFling", ex);
        }
        return result;
    }
}
