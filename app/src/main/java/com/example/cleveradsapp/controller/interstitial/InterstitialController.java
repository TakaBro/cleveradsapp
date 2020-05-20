package com.example.cleveradsapp.controller.interstitial;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.cleveradsapp.ActivityHolder;
import com.example.cleveradsapp.controller.Controller;
import com.example.cleveradsapp.loader.CascadeListener;
import com.example.cleveradsapp.loader.crazy.CrazyCascade;
import com.example.cleveradsapp.presenter.PresenterListener;
import com.example.cleveradsapp.presenter.interstitial.InterstitialPresenter;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.interstitial.InterstitialNetworkAdFactory;
import com.unity3d.ads.UnityAds;

import java.util.LinkedHashMap;

public class InterstitialController implements Controller, PresenterListener, CascadeListener {

    private String LOGTAG = "TestAds_Controller";
    private NetworkAd adLoaded;
    private CrazyCascade cascade;
    private InterstitialPresenter presenter;
    private InterstitialNetworkAdFactory interstitialNetworkAdFactory;
    private ActivityHolder activityHolder;

    public InterstitialController(LinkedHashMap<String, String> tags) {
        activityHolder = ActivityHolder.getInstance();
        setupCascade(this);
        setupPresenter(this);
        interstitialNetworkAdFactory = new InterstitialNetworkAdFactory();
        createNetworkAds(tags);
        loadAd();
    }

    public void setupPresenter(PresenterListener listener) {
        presenter = new InterstitialPresenter();
        presenter.addListener(listener);
    }

    public void setupCascade(CascadeListener listener) {
        cascade = new CrazyCascade();
        cascade.addListener(listener);
    }

    public void createNetworkAds(LinkedHashMap<String, String> tags) {
        for (int tagsIndex = 0; tagsIndex < tags.size(); tagsIndex++) {
            cascade.networkAdsList.add(interstitialNetworkAdFactory.createInterstitialNetworkAd(
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
        if (adLoaded != null) {
            Log.d(LOGTAG, "Showing Interstitial Ad");
            presenter.showAd(adLoaded);
        } else if(UnityAds.isReady ("video")) {
            UnityAds.show(activityHolder.getCurrentActivity(), "video");
        } else {
            Log.d(LOGTAG, "Interstitial Ad is NULL");
        }
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
