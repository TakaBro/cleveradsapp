package com.example.cleveradsapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;

import android.os.Bundle;
import android.view.View;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    CleverAdsPlugin cleverAdsPlugin;

    // Create a HashMap object for Tags
    LinkedHashMap<String, String> poolTags = new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poolTags.put("ca-app-pub-4452499906520493/1234567890", "AdMob");            //error
        poolTags.put("ca-app-pub-3940256099942544/1033173712", "AdMob");            //test
        poolTags.put("1675872-video", "UnityAds");                                  //unityads

        poolTags.put("ca-app-pub-5073389895475742/8102108072", "AdMob-AppLovin");   //mediation
        /*mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(LOGTAG, "AdMob Interstitial LOADED"); // Code to be executed when an ad finishes loading.
                //loaderListener.adLoaded(AdMobInterstitialNetworkAd.this);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(LOGTAG, "AdMob Interstitial FAILED TO LOAD"); // Code to be executed when an ad request fails.
                //loaderListener.adFailedToLoad();

                switch (errorCode)
                {
                    case 0:
                        Log.d(LOGTAG, "ERROR_CODE_INTERNAL_ERROR");
                    case 1:
                        Log.d(LOGTAG, "ERROR_CODE_INVALID_REQUEST");
                    case 2:
                        Log.d(LOGTAG, "ERROR_CODE_NETWORK_ERROR");
                    case 3:
                        Log.d(LOGTAG, "ERROR_CODE_NO_FILL");
                    default:

                }
            }

        });*/

        if(cleverAdsPlugin == null){
            cleverAdsPlugin = new CleverAdsPlugin(poolTags, 1, this);
        }
    }

    public void showInterstitialAd(View view)
    {
        cleverAdsPlugin.showInsterstitialAd();
    }
}
