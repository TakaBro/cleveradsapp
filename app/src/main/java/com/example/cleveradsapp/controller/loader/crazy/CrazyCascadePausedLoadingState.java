package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;

import com.example.cleveradsapp.controller.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;

public class CrazyCascadePausedLoadingState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_PausedLoadingState";

    public CrazyCascadePausedLoadingState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    @Override
    public void onAdLoaded(NetworkAd ad) {
        crazyCascade.loadedAdIndex = crazyCascade.currentAdIndex;
        crazyCascade.currentAdIndex = 0;
        crazyCascade.loadedAd = ad;
    }

    @Override
    public void onAdFailedToLoad() {
        crazyCascade.currentAdIndex++;
        if (crazyCascade.currentAdIndex == crazyCascade.loadedAdIndex
                || crazyCascade.currentAdIndex == networkAdsList.size()) {
            crazyCascade.currentAdIndex=0;
        }
        crazyCascade.currentState = crazyCascade.pausedWaitToRetryState;
        waitAndLoadAdAgain(logTag);
        //crazyCascade.timeLimitEnded = false;
    }

    @Override
    public void resume() {
        if (crazyCascade.loadedAd != null) {
            crazyCascade.listener.adLoaded(crazyCascade.loadedAd);
            crazyCascade.currentState = crazyCascade.waitToRetryState;
            waitAndLoadAdAgain(logTag);
        }
    }

    @Override
    public void loadAd(Activity act) {
    }

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
