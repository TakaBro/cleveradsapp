package com.example.cleveradsapp.controller.interstitial;

import com.example.cleveradsapp.networkAd.NetworkAd;

public interface InterstitialControllerListener {

    void adLoaded(NetworkAd ad);

    void adFailedToLoad();
}
