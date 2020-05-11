package com.example.cleveradsapp.loader.simple;

import com.example.cleveradsapp.loader.CascadeListener;

public class SimpleCascadeReadyState extends AbstractSimpleCascadeState {

    private String logTag = "TestAds_ReadyState";

    @Override
    public void onWaitFinished() {

    }

    @Override
    public void loadAd() {
        requestAd(logTag);
    }

    @Override
    public void pause() {
        simpleCascade.currentState = simpleCascade.pausedReadyState;
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
