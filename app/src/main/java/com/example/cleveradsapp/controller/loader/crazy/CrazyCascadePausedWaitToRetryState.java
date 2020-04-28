package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;

import com.example.cleveradsapp.controller.loader.CascadeListener;

public class CrazyCascadePausedWaitToRetryState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_PausedWaitToRetryState";

    public CrazyCascadePausedWaitToRetryState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    public void resume() {
        crazyCascade.currentState = crazyCascade.waitToRetryState;
        if (crazyCascade.timeLimitEnded) {
            requestAd(logTag);
        }
    }

    @Override
    public void onWaitFinished() {
        crazyCascade.timeLimitEnded = true;
    }

    @Override
    public void loadAd(Activity act) {}

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
