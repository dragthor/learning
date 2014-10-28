package com.kriskrause.learning;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
    private TextView _appVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        String versionName;

        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException  n) {
            versionName = "?";
        }

        _appVersion = (TextView) findViewById(R.id.txt_version);
        _appVersion.setText(versionName);

        _review = (TextView) findViewById(R.id.txt_review);

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
            String url = getString(R.string.store_google) + getPackageName();

            if (LearningApplication.STORE_AMAZON) {
                url = getString(R.string.store_amazon) + getPackageName();
            }

            Uri marketUri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, marketUri) ;

            startActivity(intent);
        } catch (Exception ex) {
            Log.e(LearningApplication.TAG, "exception", ex);
        }
    }
}