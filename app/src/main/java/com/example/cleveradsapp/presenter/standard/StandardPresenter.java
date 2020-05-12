package com.example.cleveradsapp.presenter.standard;

import android.os.Handler;
import android.util.Log;

import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.example.cleveradsapp.presenter.Presenter;
import com.example.cleveradsapp.presenter.PresenterListener;

public class StandardPresenter implements Presenter, NetworkAdPresenterListener {

    private String LOG_TAG = "TestAds_SimpleCascade";
    PresenterListener listener;
    protected Runnable r;
    protected Handler handler = new Handler();
    protected long timeToRefreshAd;

    public StandardPresenter(long timeToRefreshAd) {
        this.timeToRefreshAd = timeToRefreshAd;
    }

    @Override
    public void showAd(NetworkAd ad) {
        Log.d("TestAds_Presenter", "showAd");
        ad.show();
        waitToRefreshAd();
    }

    protected void waitToRefreshAd() {
        Log.d(LOG_TAG, "waiting to refresh Ad...");
        r = new Runnable() {
            @Override
            public void run() {
                listener.refreshAd();
            }
        };
        handler.postDelayed(r, timeToRefreshAd);
    }

    @Override
    public void adOpened() {

    }

    @Override
    public void adClosed() {

    }

    public void addListener(PresenterListener listener) {
        this.listener = listener;
    }
}
