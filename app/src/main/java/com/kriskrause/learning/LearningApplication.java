package com.kriskrause.learning;

import android.app.Application;

public class LearningApplication extends Application {
    public static final boolean STORE_GOOGLE = true;
    public static final boolean STORE_AMAZON = false;

    public static final String TAG = "LEARNING";

    public static final String LAST_PREFS = "LAST_PREFS";
    public static final String CURRENT_REVIEW = "CURRENT_REVIEW";

    public static final String LAST_DISPLAY_DEFAULT = "A";
    public static final String LAST_DISPLAY_CLUE_DEFAULT = "Apple";
    public static final String LAST_MODE_DISPLAY_DEFAULT = "Letters";
}
