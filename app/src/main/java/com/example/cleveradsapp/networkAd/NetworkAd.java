package com.example.cleveradsapp.networkAd;

public interface NetworkAd {

    /*String tag= null , net = null;
    Activity activity = null;
    NetworkAdLoadListener loaderListener = null;
    NetworkAdPresenterListener presenterListener = null;*/

    void request();

    void show();

    String getTag();

    String getNet();
}
