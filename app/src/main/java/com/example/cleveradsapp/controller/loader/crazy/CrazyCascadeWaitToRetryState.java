package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;

import com.example.cleveradsapp.controller.loader.CascadeListener;

public class CrazyCascadeWaitToRetryState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_WaitToRetryState";

    public CrazyCascadeWaitToRetryState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    @Override
    public void onWaitFinished() {
        requestAd(logTag);
    }

    @Override
    public void pause() {
        crazyCascade.currentState = crazyCascade.pausedWaitToRetryState;
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
