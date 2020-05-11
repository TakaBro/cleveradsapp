package com.example.cleveradsapp.loader.simple;

import com.example.cleveradsapp.loader.CascadeListener;

public class SimpleCascadePausedWaitToRetryState extends AbstractSimpleCascadeState {

    private String logTag = "TestAds_PausedWaitToRetryState";

    @Override
    public void resume() {
        simpleCascade.currentState = simpleCascade.waitToRetryState;
        if (simpleCascade.timeLimitEnded) {
            requestAd(logTag);
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
