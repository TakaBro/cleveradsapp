package com.example.cleveradsapp.networkAd.standard;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.cleveradsapp.loader.simple.SimpleCascade;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.standard.admob.AdMobStandardNetworkAd;
import com.example.cleveradsapp.presenter.standard.StandardPresenter;

import java.util.LinkedHashMap;

public class StandardNetworkAdFactory {

    String tag, net;
    NetworkAd ad;

    public NetworkAd createStandardNetworkAd(LinkedHashMap<String,String> tags, int tagsIndex,
                                             SimpleCascade cascade, StandardPresenter presenter) {

        tag = tags.keySet().toArray()[tagsIndex].toString();
        net = tags.get(tags.keySet().toArray()[tagsIndex]);

        switch (net) {
            case "AdMob":
                Log.d("TestAds_AdFactory", "AdMobNetworkAd created");
                ad = new AdMobStandardNetworkAd(tag, net, cascade, presenter);
                break;
            case "UnityAds":
                Log.d("TestAds_AdFactory", "UnityAdsNetworkAd created");
                //ad = new UnityInterstitialNetworkAd(tag, net, cascade, presenter);
                break;
            case "AppLovin":
                Log.d("TestAds_AdFactory", "AppLovinNetworkAd created");
                //ad = new AppLovinInterstitialNetworkAd(tag, net, cascade, presenter);
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
