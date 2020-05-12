package com.example.cleveradsapp.loader.simple;

import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;

public class SimpleCascadePausedLoadingState extends AbstractSimpleCascadeState {

    private String logTag = "TestAds_PausedLoadingState";

    public SimpleCascadePausedLoadingState(SimpleCascade simpleCascade) {
        super(simpleCascade);
    }

    @Override
    public void onAdLoaded(NetworkAd ad) {
        simpleCascade.currentAdIndex = 0;
        simpleCascade.loadedAd = ad;
    }

    @Override
    public void onAdFailedToLoad() {
        simpleCascade.currentAdIndex++;
        if (simpleCascade.currentAdIndex == networkAdsList.size()) {
            simpleCascade.currentAdIndex=0;
            simpleCascade.currentState = simpleCascade.pausedWaitToRetryState;
            waitAndLoadAd(logTag);
        }
    }

    @Override
    public void resume() {
        if (simpleCascade.loadedAd != null) {
            simpleCascade.listener.adLoaded(simpleCascade.loadedAd);
            simpleCascade.currentState = simpleCascade.waitToRetryState;
            simpleCascade.loadedAd = null;
            waitAndLoadAd(logTag);
        }else {
            simpleCascade.currentState = simpleCascade.loadingState;
        }

    }

    @Override
    public void onWaitFinished() {

    }

    @Override
    public void loadAd() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
