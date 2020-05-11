package com.example.cleveradsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    CleverAdsPlugin cleverAdsPlugin;
    CleverAdsPluginConfiguration cleverAdsConfig;
    LinearLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adContainer = findViewById(R.id.banner_container);

        if(cleverAdsPlugin == null) {
            cleverAdsConfig = new CleverAdsPluginConfiguration();
            cleverAdsPlugin = new CleverAdsPlugin(cleverAdsConfig.getstandardPoolTags(),
                    cleverAdsConfig.getType(), adContainer,  this);
        }

        /*adContainer = findViewById(R.id.banner_container);

        AdMobStandardNetworkAd adMobStandardNetworkAd = new AdMobStandardNetworkAd(this,
                adContainer);
        adMobStandardNetworkAd.request();*/
    }

    public void showInterstitialAd(View view) {
        cleverAdsPlugin.showInsterstitialAd();
    }

    public void showStandardAd(View view) {
        cleverAdsPlugin.showStandardAd();
    }
}
