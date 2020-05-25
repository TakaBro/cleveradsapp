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
        Log.d(LOG_TAG, "timeToAdPresentationFinish: " + timeToAdPresentationFinish);
        this.timeToAdPresentationFinish = timeToAdPresentationFinish;
        StandardPresenterState.DISABLED_PRESENTING.setPresenter(this);
        StandardPresenterState.DISABLED_BLOCKED.setPresenter(this);
        StandardPresenterState.BLOCKED.setPresenter(this);
        StandardPresenterState.PRESENTING.setPresenter(this);
        currentState = StandardPresenterState.DISABLED_BLOCKED;
    }

    public void enable() {
        Log.d(LOG_TAG, "Enable Standard Ad");
        currentState.enable();
    }

    public void disable() {
        Log.d(LOG_TAG, "Disable Standard Ad");
        currentState.disable();
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

    public void pausePresentation() {
        Log.d("TestAds_Presenter", "Pause Standard Ad");
        currentState.pausePresentation();
    }

    public void resume() {
//        currentState.resume();
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
