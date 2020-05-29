package com.example.cleveradsapp.loader.crazy;

import com.example.cleveradsapp.loader.CascadeListener;

public class CrazyCascadePausedReadyState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_CrazyPausedReadyState";

    public CrazyCascadePausedReadyState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    @Override
    public void resume() {
        crazyCascade.currentState = crazyCascade.readyState;
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
