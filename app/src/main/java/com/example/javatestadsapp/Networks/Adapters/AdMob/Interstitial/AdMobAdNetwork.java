package com.example.javatestadsapp.Networks.Adapters.AdMob.Interstitial;

import android.app.Activity;
import android.util.Log;

import com.example.javatestadsapp.Networks.Adapters.NetAdapters;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.LinkedHashMap;

public class AdMobAdNetwork implements NetAdapters {

    private String TAG = "TestAds_AdmobAds";
    //AppLovinInterstitialAdDialog interstitialAd;
    public InterstitialAd mInterstitialAd;
    public InterstitialAd mInterstitialAdLoaded;

    public void createAndLoadAdMobInterstitialAd(String tag, Activity act){
        mInterstitialAdLoaded = new InterstitialAd(act);
        mInterstitialAd = new InterstitialAd(act);
        mInterstitialAd.setAdUnitId(tag);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        listenerAdMobInterstitial();
    }

    @Override
    public void requestInterstitialAd(String tag, Activity act) {
        mInterstitialAdLoaded = new InterstitialAd(act);
        mInterstitialAd = new InterstitialAd(act);
        mInterstitialAd.setAdUnitId(tag);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        listenerAdMobInterstitial();
    }

    @Override
    public void showInterstitial() {

    }

    public void listenerAdMobInterstitial() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAdLoaded = mInterstitialAd;
                Log.d(TAG, "AdMob Interstitial LOADED"); // Code to be executed when an ad finishes loading.
                Log.d(TAG, "--------------------------------------");
                /*selectedAd = i; i = 0;
                waitToCascateAgain();*/
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(TAG, "adMobInterstitial FAILED TO LOAD"); // Code to be executed when an ad request fails.
                /*i++;
                if(i==selectedAd){
                    waitToCascateAgain();
                    printSeparation = true;
                    i=0;
                }else{
                    createAndLoadAdMobInterstitialAd(poolTags.keySet().toArray()[i].toString());
                }*/

                switch (errorCode)
                {
                    case 0:
                        Log.d(TAG, "ERROR_CODE_INTERNAL_ERROR");
                    case 1:
                        Log.d(TAG, "ERROR_CODE_INVALID_REQUEST");
                    case 2:
                        Log.d(TAG, "ERROR_CODE_NETWORK_ERROR");
                    case 3:
                        Log.d(TAG, "ERROR_CODE_NO_FILL");
                    default:
                        /*if(printSeparation){
                            Log.d(TAG, "--------------------------------------");
                            printSeparation = false;
                        }*/
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }
}
