package com.example.cleveradsapp.controller.standard;

import android.util.Log;

import com.example.cleveradsapp.controller.Controller;
import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.loader.simple.SimpleCascade;
import com.example.cleveradsapp.presenter.PresenterListener;
import com.example.cleveradsapp.presenter.standard.StandardPresenter;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.standard.StandardNetworkAdFactory;

import java.util.LinkedHashMap;

public class StandardController implements Controller, PresenterListener, CascadeListener  {

    private String LOGTAG = "TestAds_Controller";
    private NetworkAd adLoaded;
    private SimpleCascade cascade;
    private StandardPresenter presenter;
    private StandardNetworkAdFactory standardNetworkAdFactory;

    public StandardController(LinkedHashMap<String, String> tags) {
        setupCascade(this);
        setupPresenter(this);
        standardNetworkAdFactory = new StandardNetworkAdFactory();
        createNetworkAds(tags);
        loadAd();
    }

    public void setupPresenter(PresenterListener listener) {
        presenter = new StandardPresenter();
        presenter.addListener(listener);
    }

    public void setupCascade(CascadeListener listener) {
        cascade = new SimpleCascade();
        cascade.addListener(listener);
    }

    public void createNetworkAds(LinkedHashMap<String, String> tags) {
        for (int tagsIndex = 0; tagsIndex < tags.size(); tagsIndex++) {
            cascade.networkAdsList.add(standardNetworkAdFactory.createStandardNetworkAd(
                    tags, tagsIndex, cascade, presenter));
        }
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
    }

    @Override
    public void resume() {
        Log.d(LOGTAG, "Continue Cascade");
        cascade.resume();
    }

    @Override
    public void showAd() {
    }

    //PresenterListener
    @Override
    public void adOpened() {

    }

    @Override
    public void adClosed() {
        Log.d(LOGTAG, "Ad Closed and Reset Cascade");
        adLoaded = null;
        cascade.reset();
    }

    //CascadeListener
    @Override
    public void adLoaded(NetworkAd ad) {
        Log.d(LOGTAG, "Ad Loaded");
        Log.d(LOGTAG, "--------------------------------------");
        adLoaded = ad;
    }

    @Override
    public void adFailedToLoad() {

    }
}
