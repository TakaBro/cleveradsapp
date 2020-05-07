package com.example.cleveradsapp.loader.crazy;

import android.util.Log;
import com.example.cleveradsapp.loader.CascadeListener;
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
        waitAndLoadAd(logTag);
    }

    @Override
    public void onAdFailedToLoad() {
        Log.d(logTag, "onAdFailedLoaded()");
        crazyCascade.currentAdIndex++;
        if (crazyCascade.currentAdIndex == crazyCascade.loadedAdIndex
                || crazyCascade.currentAdIndex == networkAdsList.size()) {
            crazyCascade.currentAdIndex=0;
            crazyCascade.currentState = crazyCascade.waitToRetryState;
            waitAndLoadAd(logTag);
        } else {
            requestAd(logTag);
        }
    }

    @Override
    public void pause() {
        crazyCascade.currentState = crazyCascade.pausedLoadingState;
    }

    @Override
    public void loadAd() {

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

    @Override
    public void onWaitFinished() {

    }
}
