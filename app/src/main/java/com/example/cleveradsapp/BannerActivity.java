package com.example.cleveradsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

public class BannerActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.registerActivityLifecycleCallbacks(ActivityHolder.getInstance());
        if(getIntent().getExtras() != null) {
            this.registerActivityLifecycleCallbacks((CleverAdsPlugin)
                    getIntent().getSerializableExtra("clever_ads_plugin"));
        }
        setContentView(R.layout.activity_banner);
    }
}
