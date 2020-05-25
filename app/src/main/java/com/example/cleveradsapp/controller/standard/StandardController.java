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
    private Boolean updateAd = false;
    protected NetworkAd loadedAd = null;
    protected NetworkAd presentationAd = null;

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

    public void disableStandardAd() {
        presenter.disable();
    }

    public void setupCascade(CascadeListener listener, long adWaitTimeLimit) {
        cascade = new SimpleCascade(adWaitTimeLimit);
        cascade.addListener(listener);
    }

    public void setupPresenter(StandardPresenterListener listener, long timeToRefreshAd) {
        presenter = new StandardPresenter(10000);
        presenter.addListener(listener);
    }

    public void createNetworkAds(LinkedHashMap<String, String> tags) {
        for (int tagsIndex = 0; tagsIndex < tags.size(); tagsIndex++) {
            cascade.networkAdsList.add(standardNetworkAdFactory.createStandardNetworkAd(
                    tags, tagsIndex, cascade, presenter));
        }
    }

    public void onContainerAppeared(LinearLayout adContainer) {
        Log.d(LOGTAG, "onContainerAppeared");
        if (presentationAd != null) { // PRESENTING
            presenter.resumePresentation(presentationAd, adContainer);
        } else if(loadedAd != null) { // NOT STARTED
            presenter.startPresentation(loadedAd, adContainer);
        } else {
            Log.d(LOGTAG, "Standard Ad is NULL");
        }
    }

    public void onContainerDisappeared() {
        Log.d(LOGTAG, "onContainerDisappeared");
        presenter.pausePresentation();
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
        presenter.pausePresentation();
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
    public void onAdPresentationStarted() {
        presentationAd = loadedAd;
        cascade.loadAd();
    }

    @Override
    public void onAdPresentationFinished(LinearLayout adContainer) {
        Log.d(LOGTAG, "time to refresh Ad");
        if(loadedAd != null) { // LOADED
            presentationAd = loadedAd;
            presenter.startPresentation(presentationAd, adContainer);
        } else { //LOADING
            updateAd = true;
        }
    }

    //CascadeListener
    @Override
    public void adLoaded(NetworkAd ad) {
        Log.d(LOGTAG, "Ad Loaded");
        Log.d(LOGTAG, "--------------------------------------");
        if (updateAd) { // LOADING
            loadedAd = ad;
            presentationAd = loadedAd;
            presenter.startPresentation(presentationAd, presenter.adContainer);
            updateAd = false;
        } else { // LOADED
            loadedAd = ad;
        }
    }

    @Override
    public void adFailedToLoad() {

    }
}
