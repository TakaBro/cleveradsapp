package com.example.cleveradsapp.loader.simple;

import com.example.cleveradsapp.loader.CascadeListener;

public class SimpleCascadePausedWaitToRetryState extends AbstractSimpleCascadeState {

    private String logTag = "TestAds_PausedWaitToRetryState";

    public SimpleCascadePausedWaitToRetryState(SimpleCascade simpleCascade) {
        super(simpleCascade);
    }

    @Override
    public void resume() {
        simpleCascade.currentState = simpleCascade.waitToRetryState;
        if (simpleCascade.timeLimitEnded) {
            requestAd(logTag);
        }
    }

    @Override
    public void onWaitFinished() {
        simpleCascade.timeLimitEnded = true;
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
