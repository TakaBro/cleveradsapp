package com.example.cleveradsapp.controller;

import com.example.cleveradsapp.networkAd.NetworkAd;

public interface InterstitialControllerListener {

    void adLoaded(NetworkAd ad);

    void adFailedToLoad();
}
