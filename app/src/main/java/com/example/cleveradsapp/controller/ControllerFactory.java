package com.example.cleveradsapp.controller;

import android.app.Activity;
import android.util.Log;

import java.util.LinkedHashMap;

public class ControllerFactory {

    InterstitialController controller;

    public InterstitialController createController(LinkedHashMap<String, String> tags, int adType,
                                                    Activity activity) {
        //0 standard, 1 interstitial and 2 rewarded
        switch (adType) {
            case 0:
                Log.d("TestAds_ControlFactory", "Standard Controller created");
                // new Standard Controller
                break;
            case 1:
                Log.d("TestAds_ControlFactory", "Interstitial Controller created");
                controller = new InterstitialController(tags, adType, activity);
                break;
            case 2:
                Log.d("TestAds_ControlFactory", "AppLovinNetworkAd created");
                // new Interstitial Controller
                break;
            default:
                //Log.d(LOGTAG, "--------------------------------------");
        }
        return controller;
    }
}
