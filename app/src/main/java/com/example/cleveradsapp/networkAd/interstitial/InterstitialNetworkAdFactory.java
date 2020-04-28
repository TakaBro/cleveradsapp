package com.example.cleveradsapp.networkAd.interstitial;

import android.app.Activity;
import android.util.Log;

import com.example.cleveradsapp.controller.loader.crazy.CrazyCascade;
import com.example.cleveradsapp.controller.presenter.interstitial.InterstitialPresenter;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.interstitial.admob.AdMobInterstitialNetworkAd;
import com.example.cleveradsapp.networkAd.interstitial.applovin.AppLovinInterstitialNetworkAd;
import com.example.cleveradsapp.networkAd.interstitial.unityads.UnityInterstitialNetworkAd;

import java.util.LinkedHashMap;

public class InterstitialNetworkAdFactory {

    String tag, net;
    NetworkAd ad;

    public NetworkAd createInterstitialNetworkAd(LinkedHashMap<String,String> tags,
                                                 int tagsIndex,
                                                 CrazyCascade cascade,
                                                 InterstitialPresenter presenter,
                                                 Activity activity) {

        tag = tags.keySet().toArray()[tagsIndex].toString();
        net = tags.get(tags.keySet().toArray()[tagsIndex]);

        switch (net) {
            case "AdMob":
                Log.d("TestAds_AdFactory", "AdMobNetworkAd created");
                ad = new AdMobInterstitialNetworkAd(tag, net, cascade, presenter, activity);
                break;
            case "UnityAds":
                Log.d("TestAds_AdFactory", "UnityAdsNetworkAd created");
                ad = new UnityInterstitialNetworkAd(tag, net, cascade, presenter, activity);
                break;
            case "AppLovin":
                Log.d("TestAds_AdFactory", "AppLovinNetworkAd created");
                ad = new AppLovinInterstitialNetworkAd(tag, net, cascade, presenter, activity);
                break;
            case "AdMob-AppLovin":
                Log.d("TestAds_AdFactory", "AdMobAppLovinNetworkAd created");
                break;
            default:
                //Log.d(LOGTAG, "--------------------------------------");
        }
        return ad;
    }

}
