package com.example.cleveradsapp.loader.simple;

import android.util.Log;

import com.example.cleveradsapp.loader.CascadeState;
import com.example.cleveradsapp.networkAd.NetworkAd;

public abstract class AbstractSimpleCascadeState implements CascadeState {

    protected SimpleCascade simpleCascade;
    protected Runnable r;

    protected void requestAd(String logTag) {
        simpleCascade.currentState = simpleCascade.loadingState;
        Log.d(logTag, "Load networkAd: " + simpleCascade.currentAdIndex
                + " " + simpleCascade.networkAdsList.get(simpleCascade.currentAdIndex).getTag()
                + " " + simpleCascade.networkAdsList.get(simpleCascade.currentAdIndex).getNet());
        simpleCascade.networkAdsList.get(simpleCascade.currentAdIndex).request();
    }

    protected void waitAndLoadAd(final String logTag) {
        Log.d(logTag, "waitingToLoadAd()...");
        r = new Runnable() {
            @Override
            public void run() {
                simpleCascade.currentState.onWaitFinished();
            }
        };
        simpleCascade.handler.postDelayed(r, simpleCascade.TIME_LIMIT);
    }

    public void onAdLoaded(NetworkAd ad) {}
    public void onAdFailedToLoad() {}
}
