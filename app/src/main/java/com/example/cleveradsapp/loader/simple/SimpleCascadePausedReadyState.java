package com.example.cleveradsapp.loader.simple;

import com.example.cleveradsapp.loader.CascadeListener;

public class SimpleCascadePausedReadyState extends AbstractSimpleCascadeState {

    public SimpleCascadePausedReadyState(SimpleCascade simpleCascade) {
        super(simpleCascade);
    }

    @Override
    public void resume() {
        simpleCascade.currentState = simpleCascade.readyState;
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
