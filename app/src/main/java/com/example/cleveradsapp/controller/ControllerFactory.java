package com.example.cleveradsapp.controller;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.cleveradsapp.controller.interstitial.InterstitialController;
import com.example.cleveradsapp.controller.standard.StandardController;

import java.util.LinkedHashMap;

public class ControllerFactory {

    Controller controller;

    public Controller createController(LinkedHashMap<String, String> tags, int adType, long adWaitTimeLimit, LinearLayout adContainer) {
        //0 standard, 1 interstitial and 2 rewarded
        switch (adType) {
            case 0:
                Log.d("TestAds_ControlFactory", "Standard Controller created");
                controller = new StandardController(tags, adWaitTimeLimit, adContainer);
                break;
            case 1:
                Log.d("TestAds_ControlFactory", "Interstitial Controller created");
                controller = new InterstitialController(tags);
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
