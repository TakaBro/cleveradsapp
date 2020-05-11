package com.example.cleveradsapp.loader.simple;

import android.os.Handler;
import android.util.Log;

import com.example.cleveradsapp.loader.Cascade;
import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;

public class SimpleCascade implements Cascade, NetworkAdLoadListener {

    private String LOG_TAG = "TestAds_SimpleCascade";
    protected long TIME_LIMIT = 5000;

    protected NetworkAd loadedAd = null;
    protected boolean timeLimitEnded = false;
    protected int currentAdIndex = 0;
    protected Handler handler = new Handler();
    protected CascadeListener listener;
    protected AbstractSimpleCascadeState currentState;
    protected SimpleCascadeReadyState readyState = new SimpleCascadeReadyState(this);
    protected SimpleCascadePausedReadyState pausedReadyState= new SimpleCascadePausedReadyState(this);
    protected SimpleCascadeLoadingState loadingState = new SimpleCascadeLoadingState(this);
    protected SimpleCascadePausedLoadingState pausedLoadingState = new SimpleCascadePausedLoadingState(this);
    protected SimpleCascadeWaitToRetryState waitToRetryState = new SimpleCascadeWaitToRetryState(this);
    protected SimpleCascadePausedWaitToRetryState pausedWaitToRetryState = new SimpleCascadePausedWaitToRetryState(this);

    public SimpleCascade() {
        Log.d("TestAds_SimpleCascade", "SimpleCascade instance created");
        currentState = readyState;
    }

    @Override
    public void loadAd() {
        currentState.loadAd();
    }

    @Override
    public void pause() {
        currentState.pause();
    }

    @Override
    public void resume() {
        currentState.resume();
    }

    @Override
    public void reset() {
        Log.d(LOG_TAG, "reset()");
        currentState = readyState;
        loadAd();
    }

    @Override
    public void addListener(CascadeListener cascadeListener) {
        this.listener = cascadeListener;
    }

    @Override
    public void adLoaded(NetworkAd ad) {
        currentState.onAdLoaded(ad);
    }

    @Override
    public void adFailedToLoad() {
        Log.d(LOG_TAG, "currentState.onAdLoaded(ad)");
        currentState.onAdFailedToLoad();
    }
}
