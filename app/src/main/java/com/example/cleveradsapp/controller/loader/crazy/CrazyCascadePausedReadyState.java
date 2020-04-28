package com.example.cleveradsapp.controller.loader.crazy;

import com.example.cleveradsapp.controller.loader.CascadeListener;

public class CrazyCascadePausedReadyState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_PausedReadyState";

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
}
