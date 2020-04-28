package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import com.example.cleveradsapp.controller.loader.Cascade;
import com.example.cleveradsapp.networkAd.NetworkAd;

public abstract class AbstractCrazyCascadeState implements Cascade {

    protected static CrazyCascade crazyCascade;

    public AbstractCrazyCascadeState(CrazyCascade crazyCascade) {
        this.crazyCascade = crazyCascade;
    }

    @Override
    public void loadAd(Activity activity) {}
    public void pause() {}
    public void onAdLoaded(NetworkAd ad) {}
    public void onAdFailedToLoad() {}

    protected void requestAd(String logTag) {
        crazyCascade.currentState = crazyCascade.loadingState;
        Log.d(logTag, "Load networkAd: " + crazyCascade.currentAdIndex
                + " " + crazyCascade.networkAdsList.get(crazyCascade.currentAdIndex).getTag()
                + " " + crazyCascade.networkAdsList.get(crazyCascade.currentAdIndex).getNet());
        crazyCascade.networkAdsList.get(crazyCascade.currentAdIndex).request();
    }

    protected void waitAndLoadAdAgain(final String logTag) {
        Log.d(logTag, "waitingToLoadAdAgain()...");
        //crazyCascade.currentState = crazyCascade.waitToRetryState;
        //to do PAUSED AND WAIT TO RETRY
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(crazyCascade.currentState != crazyCascade.readyState) {
                    crazyCascade.currentState.loadAd(crazyCascade.activity);
                }
            }
        };
        crazyCascade.handler.postDelayed(r, crazyCascade.TIME_LIMIT);
    }
}
