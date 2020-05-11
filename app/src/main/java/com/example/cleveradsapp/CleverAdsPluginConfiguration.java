package com.example.cleveradsapp;

import java.util.LinkedHashMap;

public class CleverAdsPluginConfiguration {

    // Create a HashMap object for Standard Tags
    private LinkedHashMap<String, String> standardPoolTags = new LinkedHashMap<String, String>();

    // Create a HashMap object for Interstital Tags
    private LinkedHashMap<String, String> interstitialPoolTags = new LinkedHashMap<String, String>();

    private int type;

    public CleverAdsPluginConfiguration() {
        fillPoolTags();
    }

    public void fillPoolTags() {
        //fillInterstitialPoolTags();
        fillStandardPoolTags();
    }

    public void fillStandardPoolTags() {
        standardPoolTags.put("ca-app-pub-4452499906520493/1234567890", "AdMob");            //error
        standardPoolTags.put("ca-app-pub-3940256099942544/6300978111", "AdMob");            //test
        type = 0;
    }

    public void fillInterstitialPoolTags() {
        interstitialPoolTags.put("ca-app-pub-4452499906520493/1234567890", "AdMob");            //error
        interstitialPoolTags.put("ca-app-pub-3940256099942544/1033173712", "AdMob");            //test
        interstitialPoolTags.put("1675872-video", "UnityAds");                                  //unityads
        interstitialPoolTags.put("ca-app-pub-5073389895475742/8102108072", "AdMob-AppLovin");   //mediation
        type = 1;
    }

    public LinkedHashMap<String, String> getstandardPoolTags() {
        return standardPoolTags;
    }

    public LinkedHashMap<String, String> getInterstitialPoolTags() {
        return interstitialPoolTags;
    }

    public int getType() {
        return type;
    }
}
