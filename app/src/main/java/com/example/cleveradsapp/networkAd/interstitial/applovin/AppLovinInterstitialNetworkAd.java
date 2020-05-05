package com.example.cleveradsapp.networkAd.interstitial.applovin;

import android.app.Activity;
import android.util.Log;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.example.cleveradsapp.CleverAdsPlugin;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;

public class AppLovinInterstitialNetworkAd implements AppLovinAdLoadListener, AppLovinAdDisplayListener,
        AppLovinAdClickListener, NetworkAd {

    private String LOGTAG = "TestAds_AppLovinAds";
    private String tag, net;
    private Activity activity;
    private NetworkAdLoadListener loaderListener;
    private NetworkAdPresenterListener presenterListener;
    private AppLovinAd loadedAd;
    private AppLovinInterstitialAdDialog interstitialAd;

    public AppLovinInterstitialNetworkAd(String tag, String net, NetworkAdLoadListener l_listener,
                                         NetworkAdPresenterListener p_Listener, Activity activity) {
        this.tag = tag;
        this.net = net;
        this.activity = activity;
        this.loaderListener = l_listener;
        this.presenterListener = p_Listener;
    }

    @Override
    public void request() {
        AppLovinSdk.getInstance( CleverAdsPlugin.getCurrentActivity() )
                .getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL,
                AppLovinInterstitialNetworkAd.this);
    }

    @Override
    public void show() {
        Log.d(LOGTAG, "APPLOVIN show()");
        interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( CleverAdsPlugin.getCurrentActivity() ),
                CleverAdsPlugin.getCurrentActivity() );
        interstitialAd.setAdDisplayListener(this);
        interstitialAd.setAdClickListener(this);
        interstitialAd.show();
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public String getNet() {
        return this.net;
    }

    @Override
    public void adReceived(AppLovinAd ad)
    {
        Log.d(LOGTAG, "APPLOVIN InterstitialAd LOADED"); // Ad successfully loaded.
        loadedAd = ad;
        loaderListener.adLoaded(AppLovinInterstitialNetworkAd.this);
    }

    @Override
    public void failedToReceiveAd(int errorCode)
    {
        // Look at AppLovinErrorCodes.java for list of error codes.
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        Log.d(LOGTAG, "APPLOVIN InterstitialAd adDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        Log.d(LOGTAG, "APPLOVIN InterstitialAd adHidden");
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        Log.d(LOGTAG, "APPLOVIN InterstitialAd adClicked");
    }
}
