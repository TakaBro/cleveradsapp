package com.example.cleveradsapp.controller;

import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.cleveradsapp.cascade.CascadeLoader.CascadeLoader;
import com.example.cleveradsapp.controller.loader.CascadeListener;
import com.example.cleveradsapp.controller.loader.crazy.CrazyCascade;
import com.example.cleveradsapp.controller.presenter.PresenterListener;
import com.example.cleveradsapp.controller.presenter.interstitial.InterstitialPresenter;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.interstitial.InterstitialNetworkAdFactory;
import com.unity3d.ads.UnityAds;

import java.util.LinkedHashMap;

public class InterstitialController extends AppCompatActivity implements PresenterListener, CascadeListener {
    private String LOGTAG = "TestAds_Controller";
    private Activity activity;
    private NetworkAd adLoaded;
    private CrazyCascade cascade;
    private InterstitialPresenter presenter;
    private InterstitialNetworkAdFactory interstitialNetworkAdFactory;

    public InterstitialController(LinkedHashMap<String, String> tags, int adType, Activity activity) {
        this.activity = activity;
        setupCascade(adType, activity, this);
        setupPresenter(this);
        interstitialNetworkAdFactory = new InterstitialNetworkAdFactory();
        createNetworkAds(tags, activity);
        loadAd(activity);
    }

    public void setupPresenter(PresenterListener listener) {
        presenter = new InterstitialPresenter();
        presenter.addListener(listener);
    }

    public void setupCascade(int adType, Activity activity,
                             CascadeListener listener) {
        //0 standard, 1 interstitial and 2 rewarded
        if(adType == 0) {
            /*cascade = new SimpleCascade(tags, adType, activity);
            cascade.addListener(listener);*/
        } else {
            cascade = new CrazyCascade(activity);
            cascade.addListener(listener);
        }
    }

    public void createNetworkAds(LinkedHashMap<String, String> tags, Activity activity) {
        for (int tagsIndex = 0; tagsIndex < tags.size(); tagsIndex++) {
            cascade.networkAdsList.add(interstitialNetworkAdFactory.createInterstitialNetworkAd(
                    tags, tagsIndex, cascade, presenter, activity));
        }
    }

    public void loadAd(Activity activity) {
        Log.d(LOGTAG, "loading Ad ...");
        cascade.loadAd(activity);
    }

    public void pause() {
        Log.d(LOGTAG, "Pause Cascade");
        cascade.pause();
    }

    public void resume() {
        Log.d(LOGTAG, "Continue Cascade");
        cascade.resume();
    }

    public void showAd() {
        if (adLoaded != null) {
            Log.d(LOGTAG, "Showing Interstitial Ad");
            presenter.showAd(adLoaded);
        } else if(UnityAds.isReady ("video")) {
            UnityAds.show(activity, "video");
        } else {
            Log.d(LOGTAG, "Ad is NULL");
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
