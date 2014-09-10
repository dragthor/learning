package com.kriskrause.learning;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.lang.Exception;
import android.util.Log;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

public class AboutActivity extends Activity implements OnClickListener {
    private TextView _review;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        _review = (TextView) findViewById(R.id.txt_Review);

        SpannableString spanString = new SpannableString(getResources().getString(R.string.about_review));
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);

        _review.setText(spanString);
        _review.setOnClickListener(this);
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
    public void onClick(View v) {
        try {
            Uri marketUri = Uri.parse("market://details?id=" + getPackageName());

            Intent intent = new Intent(Intent.ACTION_VIEW, marketUri) ;

            startActivity(intent);
        } catch (Exception ex) {
            Log.e(MainActivity.TAG, "exception", ex);
        }
    }
}