package com.example.cleveradsapp.loader.crazy;

import com.example.cleveradsapp.loader.CascadeListener;
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
            crazyCascade.currentState = crazyCascade.pausedWaitToRetryState;
            waitAndLoadAd(logTag);
        }
    }

    @Override
    public void resume() {
        if (crazyCascade.loadedAd != null) {
            crazyCascade.listener.adLoaded(crazyCascade.loadedAd);
            crazyCascade.currentState = crazyCascade.waitToRetryState;
            crazyCascade.loadedAd = null;
            waitAndLoadAd(logTag);
        }
    }

    @Override
    public void loadAd() {
    }

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }

    @Override
    public void onWaitFinished() {

    }
}
