package com.example.cleveradsapp.controller;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.cleveradsapp.controller.interstitial.InterstitialController;
import com.example.cleveradsapp.controller.standard.StandardController;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class ControllerFactory {

    Controller controller;
    StandardController standardController;
    InterstitialController interstitialController;

    public Controller createController(LinkedHashMap<String, String> tags, int adType, long adWaitTimeLimit) {
        //0 standard, 1 interstitial and 2 rewarded
        switch (adType) {
            case 0:
                controller = createStandardController(tags, adWaitTimeLimit);
                break;
            case 1:
                Log.d("TestAds_ControlFactory", "Interstitial Controller created");
                controller = createInterstitialController(tags);
                break;
            case 2:
                Log.d("TestAds_ControlFactory", "Rewarded Controller created");
                // Rewarded Controller
                break;
            default:
                //Log.d(LOGTAG, "--------------------------------------");
        }
        return controller;
    }

    public StandardController createStandardController(LinkedHashMap<String, String> tags, long adWaitTimeLimit) {
        Log.d("TestAds_ControlFactory", "Standard Controller created");
        standardController = new StandardController(tags, adWaitTimeLimit);
        return standardController;
    }

    public InterstitialController createInterstitialController(LinkedHashMap<String, String> tags) {
        Log.d("TestAds_ControlFactory", "Standard Controller created");
        interstitialController = new InterstitialController(tags);
        return interstitialController;
    }
}
