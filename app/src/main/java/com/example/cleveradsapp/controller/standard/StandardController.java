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
    protected NetworkAd loadedAd = null;
    protected NetworkAd presentedAd = null;

    public StandardController(LinkedHashMap<String, String> tags, long adWaitTimeLimit) {
        setupCascade(this, adWaitTimeLimit);
        setupPresenter(this, adWaitTimeLimit);
        standardNetworkAdFactory = new StandardNetworkAdFactory();
        createNetworkAds(tags);
        loadAd();
    }

    public void enableStandardAd() {
        presenter.enable();
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
        if (presentedAd != null) {
            presenter.resumePresentation(presentedAd, adContainer);
        } else if(loadedAd != null) {
            presentedAd = loadedAd;
            presenter.startPresentation(presentedAd, adContainer);
            cascade.loadAd();
        } else {
            Log.d(LOGTAG, "Standard Ad is NULL");
        }
    }

    public void onContainerDisappeared() {
        Log.d(LOGTAG, "Pause Standard Ad");
        presenter.pause();
    }

    @Override
    public void showAd() {
        enablePresentation();
    }

    public void enablePresentation() {

    }

    @Override
    public void loadAd() {
        Log.d(LOGTAG, "loading Ad ...");
        cascade.loadAd();
    }

    @Override
    public void pause() {
        Log.d(LOGTAG, "Pause Standard Ad");
        cascade.pause();
        presenter.pause();
    }

    @Override
    public void resume() {
        Log.d(LOGTAG, "Continue Standard Ad");
        cascade.resume();
        presenter.resume();
    }

    //PresenterListener
    @Override
    public void adOpened() {
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
        //checks if has loaded ad
        if(loadedAd != null) {
            //show new ad and start loading another ad
            presentedAd = loadedAd;
            presenter.startPresentation(presentedAd, adContainer);
            cascade.reset();
            cascade.loadAd();
        }
    }

    //CascadeListener
    @Override
    public void adLoaded(NetworkAd ad) {
        Log.d(LOGTAG, "Ad Loaded");
        Log.d(LOGTAG, "--------------------------------------");
        loadedAd = ad;
        //check if needs to show ad
        if (presentedAd == null) {
            presentedAd = loadedAd;
            presenter.startPresentation(presentedAd, presenter.adContainer);
        }
    }

    @Override
    public void adFailedToLoad() {

    }
}
