package com.example.cleveradsapp.loader.simple;

import android.util.Log;

import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;

public class SimpleCascadeLoadingState extends AbstractSimpleCascadeState {

    private String logTag = "TestAds_LoadingState";

    public SimpleCascadeLoadingState(SimpleCascade simpleCascade) {
        super(simpleCascade);
    }

    @Override
    public void onAdLoaded(NetworkAd ad) {
        Log.d(logTag, "onAdLoaded(ad)");
        simpleCascade.currentAdIndex = 0;
        simpleCascade.listener.adLoaded(ad);
        simpleCascade.currentState = simpleCascade.waitToRetryState;
        waitAndLoadAd(logTag);
    }

    @Override
    public void onAdFailedToLoad() {
        Log.d(logTag, "onAdFailedLoaded()");
        simpleCascade.currentAdIndex++;
        if (simpleCascade.currentAdIndex == networkAdsList.size()) {
            simpleCascade.currentAdIndex=0;
            simpleCascade.currentState = simpleCascade.waitToRetryState;
            waitAndLoadAd(logTag);
        } else {
            requestAd(logTag);
        }
    }

    @Override
    public void pause() {
        simpleCascade.currentState = simpleCascade.pausedLoadingState;
    }

    @Override
    public void onWaitFinished() {

    }

    @Override
    public void loadAd() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
