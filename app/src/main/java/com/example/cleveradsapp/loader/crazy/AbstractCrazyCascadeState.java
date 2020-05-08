package com.example.cleveradsapp.loader.crazy;

import android.util.Log;

import com.example.cleveradsapp.loader.CascadeState;
import com.example.cleveradsapp.networkAd.NetworkAd;

public abstract class AbstractCrazyCascadeState implements CascadeState {

    protected static CrazyCascade crazyCascade;
    protected static Runnable r;

    public AbstractCrazyCascadeState(CrazyCascade crazyCascade) {
        this.crazyCascade = crazyCascade;
    }

    protected void requestAd(String logTag) {
        crazyCascade.currentState = crazyCascade.loadingState;
        Log.d(logTag, "Load networkAd: " + crazyCascade.currentAdIndex
                + " " + crazyCascade.networkAdsList.get(crazyCascade.currentAdIndex).getTag()
                + " " + crazyCascade.networkAdsList.get(crazyCascade.currentAdIndex).getNet());
        crazyCascade.networkAdsList.get(crazyCascade.currentAdIndex).request();
    }

    protected void waitAndLoadAd(final String logTag) {
        Log.d(logTag, "waitingToLoadAd()...");
        r = new Runnable() {
            @Override
            public void run() {
                crazyCascade.currentState.onWaitFinished();
            }
        };
        crazyCascade.handler.postDelayed(r, crazyCascade.TIME_LIMIT);
    }

    public void cancelWaitAndLoadAd() {
        crazyCascade.handler.removeCallbacks(r);
    }

    @Override
    public void loadAd() {

    }
    public void pause() {

    }
    public void resume() {

    }
    public void reset() {

    }
    public void onAdLoaded(NetworkAd ad) {}
    public void onAdFailedToLoad() {}
}
