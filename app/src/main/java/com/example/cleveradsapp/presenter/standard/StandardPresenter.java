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
    protected NetworkAd loadedAd = null;
    protected NetworkAd currentAd = null;
    protected LinearLayout adContainer;
    protected StandardPresenterState currentState;

    public StandardPresenter(long timeToAdPresentationFinish) {
        this.timeToAdPresentationFinish = timeToAdPresentationFinish;
        currentState = StandardPresenterState.BLOCKED;
        currentState.GetPresenter(this);
    }

    public void onContainerAppeared(LinearLayout adContainer) {
        this.adContainer = adContainer;
        if (currentAd != null) {
            Log.d(LOG_TAG, "Show Container Ad");
            show(currentAd, adContainer);
        } else if (loadedAd != null){
            Log.d(LOG_TAG, "Show Loaded Ad");
            show(loadedAd, adContainer);
        } else {
            Log.d(LOG_TAG, "Standard Ad is NULL");
        }
    }

    public void onContainerDisappeared() {
        Log.d(LOG_TAG, "Hide Standard Ad");
        currentState.hide();
    }

    public void show(NetworkAd ad, LinearLayout adContainerParam) {
        Log.d("TestAds_Presenter", "show Ad");
        currentAd = ad;
        adContainer = adContainerParam;
        currentState.show();
    }

/*    public void runAdPresentationTimer() {
        startTime = System.nanoTime();
        checkAdPresentationTime();
    }

    public void checkAdPresentationTime() {
        if(remainingTime != 0) {
            waitAdPresentationFinish(remainingTime);
            remainingTime = 0;
        } else {
            waitAdPresentationFinish(timeToAdPresentationFinish);
        }
    }

    public void waitAdPresentationFinish(long timeToRefreshAdParameter) {
        Log.d(LOG_TAG, "waiting to refresh Ad...");
        r = new Runnable() {
            @Override
            public void run() {
                listener.onAdPresentationFinished(adContainer);
            }
        };
        handler.postDelayed(r, timeToRefreshAdParameter);
    }*/

    public void hide() {
        Log.d("TestAds_Presenter", "hide Ad");
        currentState.hide();
/*        saveAdPresentationTime();
        handler.removeCallbacks(r);
        adContainer.removeAllViews();*/
    }

    public void pause() {
        currentState.pause();
//        elapsedTime = System.nanoTime()-startTime;
//        handler.removeCallbacks(r);
    }

    public void resume() {
        currentState.resume();
//        if(timeToRefreshAd >= elapsedTime) {
//            waitToRefreshAd(timeToRefreshAd - elapsedTime);
//        } else {
//
//        }
    }

    @Override
    public void adOpened() {

    }

    @Override
    public void adClosed() {
        currentAd = loadedAd;
    }

    public void addListener(StandardPresenterListener listener) {
        this.listener = listener;
    }

    @Override
    public void showAd(NetworkAd ad) {

    }

    public void adLoaded(NetworkAd ad) {
        loadedAd = ad;
        //check if needs to show ad
    }
}
