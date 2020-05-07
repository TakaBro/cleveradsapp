package com.example.cleveradsapp.loader.crazy;

import com.example.cleveradsapp.loader.CascadeListener;

public class CrazyCascadeReadyState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_ReadyState";

    public CrazyCascadeReadyState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    @Override
    public void loadAd() {
        requestAd(logTag);
    }

    @Override
    public void pause() {
        crazyCascade.currentState = crazyCascade.pausedReadyState;
    }

    @Override
    public void resume() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void onWaitFinished() {

    }
}
