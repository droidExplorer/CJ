package com.androidapp.classifiedjobs.splash;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.category.CategorySelectionActivity;
import com.androidapp.classifiedjobs.helper.AdvancedSpannableString;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.activity.JobListingActivity;
import com.androidapp.classifiedjobs.login.activity.LoginActivityRevised;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    private TextView splashTxt;
    private AdvancedSpannableString advancedSpannableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        splashTxt = (TextView) findViewById(R.id.splashTxt);
        advancedSpannableString = new AdvancedSpannableString("Classified\nJOBS");
//        advancedSpannableString.setColor(Color.WHITE,"Classified");
        advancedSpannableString.setColor(Color.parseColor("#0077B5"), "JOBS");
        advancedSpannableString.setBold("JOBS");
        splashTxt.setText(advancedSpannableString);
        splashTxt.setTypeface(Functions.getTF(this));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (Prefs.with(SplashActivity.this).getBoolean(Constants.IS_LOGIN, false)) {
                    if (Prefs.with(SplashActivity.this).getBoolean(Constants.CATEGORY_SELECTED, false)) {
                        Functions.fireIntent(SplashActivity.this, JobListingActivity.class, true);
                    } else {
                        Functions.fireIntent(SplashActivity.this, CategorySelectionActivity.class, true);
                    }
                } else {
                    Functions.fireIntent(SplashActivity.this, LoginActivityRevised.class, true);
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
