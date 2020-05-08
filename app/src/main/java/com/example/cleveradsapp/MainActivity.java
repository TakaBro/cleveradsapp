package com.example.cleveradsapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cleveradsapp.networkAd.standard.admob.AdMobStandardNetworkAd;

public class MainActivity extends AppCompatActivity {

    CleverAdsPlugin cleverAdsPlugin;

    // Create a HashMap object for Tags
    LinkedHashMap<String, String> poolTags = new LinkedHashMap<String, String>();

    LinearLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        poolTags.put("ca-app-pub-4452499906520493/1234567890", "AdMob");            //error
        poolTags.put("ca-app-pub-3940256099942544/1033173712", "AdMob");            //test
        poolTags.put("1675872-video", "UnityAds");                                  //unityads
        poolTags.put("ca-app-pub-5073389895475742/8102108072", "AdMob-AppLovin");   //mediation
        if(cleverAdsPlugin == null) {
            cleverAdsPlugin = new CleverAdsPlugin(poolTags, 1, this);
        }

        /*adContainer = findViewById(R.id.banner_container);

        AdMobStandardNetworkAd adMobStandardNetworkAd = new AdMobStandardNetworkAd(this,
                adContainer);
        adMobStandardNetworkAd.request();*/
    }

    public void showInterstitialAd(View view)
    {
        cleverAdsPlugin.showInsterstitialAd();
    }
}
