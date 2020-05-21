package com.example.cleveradsapp.presenter.standard;

import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.example.cleveradsapp.presenter.Presenter;

public class StandardPresenter implements Presenter, NetworkAdPresenterListener {

    protected String LOG_TAG = "TestAds_StandardPresenter";
    protected StandardPresenterListener listener;
    protected Runnable r;
    protected Handler handler = new Handler();
    protected long startTime, elapsedTime, timeToAdPresentationFinish;
    protected long remainingTime = 0;
    protected NetworkAd ad;
    public LinearLayout adContainer;
    protected StandardPresenterState currentState;

    public StandardPresenter(long timeToAdPresentationFinish) {
        this.timeToAdPresentationFinish = timeToAdPresentationFinish;
        currentState = StandardPresenterState.DISABLED;
        currentState.setPresenter(this);
    }

    public void enable() {
        Log.d(LOG_TAG, "Enable Standard Ad");
        currentState.enable();
    }

    public void startPresentation(NetworkAd ad, LinearLayout adContainer) {
        Log.d("TestAds_Presenter", "startPresentation");
        this.adContainer = adContainer;
        this.ad = ad;
        currentState.startPresentation();
    }

    public void resumePresentation(NetworkAd ad, LinearLayout adContainer) {
        Log.d("TestAds_Presenter", "resumePresentation");
        this.adContainer = adContainer;
        this.ad = ad;
        currentState.resumePresentation();
    }

    public void pause() {
        currentState.pausePresentation();
//        elapsedTime = System.nanoTime()-startTime;
//        handler.removeCallbacks(r);
    }

    public void resume() {
//        currentState.resume();
        /*if(timeToRefreshAd >= elapsedTime) {
            waitToRefreshAd(timeToRefreshAd - elapsedTime);
        } else {

        }*/
    }

    @Override
    public void adOpened() {

    }

    @Override
    public void adClosed() {

    }

    public void addListener(StandardPresenterListener listener) {
        this.listener = listener;
    }

    @Override
    public void showAd(NetworkAd ad) {

    }
}
