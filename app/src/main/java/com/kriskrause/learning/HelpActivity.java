package com.kriskrause.learning;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.widget.TextView;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView txtMainTitle = (TextView) findViewById(R.id.titleMain);
        TextView txtMainReviews = (TextView) findViewById(R.id.titleReviews);

        SpannableString spanStringMain = new SpannableString(getResources().getString(R.string.help_mainTitle));
        spanStringMain.setSpan(new UnderlineSpan(), 0, spanStringMain.length(), 0);

        SpannableString spanStringReviews = new SpannableString(getResources().getString(R.string.help_reviewTitle));
        spanStringReviews.setSpan(new UnderlineSpan(), 0, spanStringReviews.length(), 0);

        txtMainTitle.setText(spanStringMain);
        txtMainReviews.setText(spanStringReviews);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
