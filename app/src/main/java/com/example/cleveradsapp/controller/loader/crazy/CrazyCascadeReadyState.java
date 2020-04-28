package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;
import com.example.cleveradsapp.controller.loader.CascadeListener;

public class CrazyCascadeReadyState extends AbstractCrazyCascadeState {

    private String logTag = "TestAds_ReadyState";

    public CrazyCascadeReadyState(CrazyCascade crazyCascade) {
        super(crazyCascade);
    }

    @Override
    public void loadAd(Activity activity) {
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
}
