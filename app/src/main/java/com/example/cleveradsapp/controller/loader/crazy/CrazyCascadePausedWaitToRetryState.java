package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;

import com.example.cleveradsapp.controller.loader.CascadeListener;

public class CrazyCascadePausedWaitToRetryState extends AbstractCrazyCascadeState {

    public CrazyCascadePausedWaitToRetryState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    public void resume() {
        crazyCascade.currentState = crazyCascade.waitToRetryState;
        if (crazyCascade.timeLimitEnded) {
            crazyCascade.currentState.loadAd(crazyCascade.activity);
        }
    }

    @Override
    public void loadAd(Activity act) {
        crazyCascade.timeLimitEnded = true;
    }

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
