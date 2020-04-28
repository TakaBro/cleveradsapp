package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;
import android.util.Log;
import com.example.cleveradsapp.controller.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;

public class CrazyCascadeLoadingState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_LoadingState";

    public CrazyCascadeLoadingState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    @Override
    public void onAdLoaded(NetworkAd ad) {
        Log.d(logTag, "onAdLoaded(ad)");
        crazyCascade.loadedAdIndex = crazyCascade.currentAdIndex;
        crazyCascade.currentAdIndex = 0;
        crazyCascade.listener.adLoaded(ad);
        crazyCascade.currentState = crazyCascade.waitToRetryState;
        waitAndLoadAdAgain(logTag);
    }

    @Override
    public void onAdFailedToLoad() {
        Log.d(logTag, "onAdFailedLoaded()");
        crazyCascade.currentAdIndex++;
        if (crazyCascade.currentAdIndex == crazyCascade.loadedAdIndex
                || crazyCascade.currentAdIndex == networkAdsList.size()) {
            crazyCascade.currentAdIndex=0;
            crazyCascade.currentState = crazyCascade.waitToRetryState;
            waitAndLoadAdAgain(logTag);
        } else {
            requestAd(logTag);
        }
    }

    @Override
    public void pause() {
        crazyCascade.currentState = crazyCascade.pausedLoadingState;
    }

    @Override
    public void loadAd(Activity act) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
