package com.example.javatestadsapp.Networks.Adapters.AppLovin.Interstitial;

import android.app.Activity;
import android.util.Log;

import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinAdNetwork implements AppLovinAdDisplayListener, AppLovinAdClickListener {

    private String TAG = "TestAds_AppLovinAds";
    public AppLovinAd loadedAd;
    public AppLovinInterstitialAdDialog interstitialAd;

    public void createAndLoadAppLovinInterstitialAd(Activity act){
        AppLovinSdk.getInstance( act ).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(AppLovinAd ad)
            {
                Log.d(TAG, "APPLOVIN InterstitialAd LOADED"); // Ad successfully loaded.
                loadedAd = ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode)
            {
                // Look at AppLovinErrorCodes.java for list of error codes.
            }
        } );
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        Log.d(TAG, "APPLOVIN InterstitialAd adDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        Log.d(TAG, "APPLOVIN InterstitialAd adHidden");
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        Log.d(TAG, "APPLOVIN InterstitialAd adClicked");
    }
}
