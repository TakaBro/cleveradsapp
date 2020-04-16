package com.example.cleveradsapp.networkAd;

public interface NetworkAdLoadListener {

    void adFailedToLoad();

    void adLoaded(NetworkAd ad);
}
