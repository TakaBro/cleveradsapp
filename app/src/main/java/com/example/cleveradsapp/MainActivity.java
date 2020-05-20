package com.example.cleveradsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.Serializable;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements Serializable {

    private CleverAdsPlugin cleverAdsPlugin;
    private CleverAdsPluginConfiguration cleverAdsConfig;
    private LinearLayout adContainer;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adContainer = findViewById(R.id.banner_container);
        if(cleverAdsPlugin == null) {
            cleverAdsConfig = new CleverAdsPluginConfiguration();
            cleverAdsPlugin = new CleverAdsPlugin(cleverAdsConfig.getstandardPoolTags(),
                    cleverAdsConfig.getAdType(), cleverAdsConfig.getAdWaitTimeLimit(), this);
        }
        this.registerActivityLifecycleCallbacks(ActivityHolder.getInstance());
        this.registerActivityLifecycleCallbacks(cleverAdsPlugin);
    }

    public void goToBannerActivity(View view) {
        //cleverAdsPlugin.showStandardAd();
        Intent intent = new Intent(this, BannerActivity.class);
        intent.putExtra("clever_ads_plugin", cleverAdsPlugin);
        startActivity(intent);
    }

    public void showStandardAd(View view) {
        cleverAdsPlugin.enableStandardAd(adContainer);
    }

    public void showInterstitialAd(View view) {
        cleverAdsPlugin.showInsterstitialAd();
    }


}
