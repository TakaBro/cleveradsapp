package com.example.cleveradsapp.controller.standard;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.cleveradsapp.controller.Controller;
import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.loader.simple.SimpleCascade;
import com.example.cleveradsapp.presenter.standard.StandardPresenter;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.standard.StandardNetworkAdFactory;
import com.example.cleveradsapp.presenter.standard.StandardPresenterListener;

import java.util.LinkedHashMap;

public class StandardController implements Controller, StandardPresenterListener, CascadeListener {

    private String LOGTAG = "TestAds_Controller";
    private SimpleCascade cascade;
    private StandardPresenter presenter;
    private StandardNetworkAdFactory standardNetworkAdFactory;

    public StandardController(LinkedHashMap<String, String> tags, long adWaitTimeLimit) {
        setupCascade(this, adWaitTimeLimit);
        setupPresenter(this, adWaitTimeLimit);
        standardNetworkAdFactory = new StandardNetworkAdFactory();
        createNetworkAds(tags);
        loadAd();
    }

    public void setupCascade(CascadeListener listener, long adWaitTimeLimit) {
        cascade = new SimpleCascade(adWaitTimeLimit);
        cascade.addListener(listener);
    }

    public void setupPresenter(StandardPresenterListener listener, long timeToRefreshAd) {
        presenter = new StandardPresenter(8000);
        presenter.addListener(listener);
    }

    public void createNetworkAds(LinkedHashMap<String, String> tags) {
        for (int tagsIndex = 0; tagsIndex < tags.size(); tagsIndex++) {
            cascade.networkAdsList.add(standardNetworkAdFactory.createStandardNetworkAd(
                    tags, tagsIndex, cascade, presenter));
        }
    }

    public void onContainerAppeared(LinearLayout adContainer) {
        presenter.onContainerAppeared(adContainer);
        cascade.loadAd();
    }

    public void onContainerDisappeared() {
        Log.d(LOGTAG, "Hide Standard Ad");
        presenter.onContainerDisappeared();
    }

    @Override
    public void showAd() {
/*        if (containerAd != null) {
            Log.d(LOGTAG, "Show Container Ad");
            presenter.show(containerAd, adContainer);
            cascade.loadAd();
        } else if (loadedAd != null){
            Log.d(LOGTAG, "Show Loaded Ad");
            presenter.show(loadedAd, adContainer);
            cascade.loadAd();
        } else {
            Log.d(LOGTAG, "Standard Ad is NULL");
        }*/
    }

    @Override
    public void loadAd() {
        Log.d(LOGTAG, "loading Ad ...");
        cascade.loadAd();
    }

    @Override
    public void pause() {
        Log.d(LOGTAG, "Pause Cascade");
        cascade.pause();
        presenter.pause();
    }

    @Override
    public void resume() {
        Log.d(LOGTAG, "Continue Cascade");
        cascade.resume();
        presenter.resume();
    }

    //PresenterListener
    @Override
    public void adOpened() {
        //containerAd = loadedAd;
    }

    @Override
    public void adClosed() {
        Log.d(LOGTAG, "Ad Closed");
        presenter.adClosed();
    }

    //StandardPresenterListener
    @Override
    public void onAdPresentationFinished(LinearLayout adContainer) {
        Log.d(LOGTAG, "time to refresh Ad");
        cascade.reset();
    }

    //CascadeListener
    @Override
    public void adLoaded(NetworkAd ad) {
        Log.d(LOGTAG, "Ad Loaded");
        Log.d(LOGTAG, "--------------------------------------");
        presenter.adLoaded(ad);
    }

    @Override
    public void adFailedToLoad() {

    }
}
