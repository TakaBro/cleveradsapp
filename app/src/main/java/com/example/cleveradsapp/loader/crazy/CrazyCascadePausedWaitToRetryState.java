package com.example.cleveradsapp.loader.crazy;

import com.example.cleveradsapp.loader.CascadeListener;

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
    public void loadAd() {}

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
