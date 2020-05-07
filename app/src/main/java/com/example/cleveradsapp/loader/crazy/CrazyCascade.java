package com.example.cleveradsapp.loader.crazy;

import android.os.Handler;
import android.util.Log;

import com.example.cleveradsapp.loader.Cascade;
import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;

public class CrazyCascade implements Cascade, NetworkAdLoadListener {

    private String LOG_TAG = "TestAds_CrazyCascade";
    protected long TIME_LIMIT = 5000;

    protected NetworkAd loadedAd = null;
    protected boolean timeLimitEnded = false;
    protected int loadedAdIndex = -1;
    protected int currentAdIndex = 0;
    protected Handler handler = new Handler();
    protected CascadeListener listener;
    protected AbstractCrazyCascadeState currentState;
    protected CrazyCascadeReadyState readyState = new CrazyCascadeReadyState(this);
    protected CrazyCascadePausedReadyState pausedReadyState= new CrazyCascadePausedReadyState(this);
    protected CrazyCascadeLoadingState loadingState = new CrazyCascadeLoadingState(this);
    protected CrazyCascadePausedLoadingState pausedLoadingState = new CrazyCascadePausedLoadingState(this);
    protected CrazyCascadeWaitToRetryState waitToRetryState = new CrazyCascadeWaitToRetryState(this);
    protected CrazyCascadePausedWaitToRetryState pausedWaitToRetryState = new CrazyCascadePausedWaitToRetryState(this);

    public CrazyCascade() {
        Log.d(LOG_TAG, "CrazyCascade instance created");
        currentState = readyState;
    }

    @Override
    public void loadAd() {
        currentState.loadAd();
    }

    public void pause() {
        currentState.pause();
    }

    public void resume() {
        currentState.resume();
    }

    @Override
    public void reset() {
        Log.d(LOG_TAG, "reset()");
        loadedAd = null;
        timeLimitEnded = false;
        loadedAdIndex = -1;
        currentAdIndex = 0;
        currentState.cancelWaitAndLoadAd();
        currentState = readyState;
        loadAd();
    }

    @Override
    public void addListener(CascadeListener cascadeListener) {
        this.listener = cascadeListener;
    }

    @Override
    public void adLoaded(NetworkAd ad) {
        Log.d(LOG_TAG, "currentState.onAdLoaded(ad)");
        currentState.onAdLoaded(ad);
    }

    @Override
    public void adFailedToLoad() {
        currentState.onAdFailedToLoad();
    }
}
