package com.example.cleveradsapp.controller.loader.crazy;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.example.cleveradsapp.controller.loader.Cascade;
import com.example.cleveradsapp.controller.loader.CascadeListener;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;

public class CrazyCascade implements Cascade, NetworkAdLoadListener {

    private String LOGTAG = "TestAds_CrazyCascade";
    private NetworkAd loadedAd;
    private boolean pauseCascade = false, fireAdLoadedLater = false;
    private int loadedAdIndex, currentAdIndex = 0;
    private Activity activity;
    private Handler handler = new Handler();
    private long timeLimit = 5000;
    private long checkTime = 1000;
    private long originalTimeLimit = timeLimit;
    private CascadeListener listener;

    public CrazyCascade(Activity activity){
        Log.d(LOGTAG, "CrazyCascade instance created");
        this.activity = activity;
    }

    @Override
    public void loadAd(Activity activity) {
        Log.d(LOGTAG, "Check if can load ad");

        if(!pauseCascade){
            Log.d(LOGTAG, "Load networkAd: " + currentAdIndex + " " + networkAdsList.get(currentAdIndex).getTag()
                    + " " + networkAdsList.get(currentAdIndex).getNet());
            networkAdsList.get(currentAdIndex).request();
        }else {
            Log.d(LOGTAG, "Cascade paused...");
            waitAndLoadAgain();
        }
    }

    public void waitAndLoadAgain(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Log.d(LOGTAG, "waitAndLoadAgain()...");
                loadAd(activity);
            }
        };
        handler.postDelayed(r, timeLimit);
    }

    public void pause() {
        this.pauseCascade = true;
        timeLimit = checkTime;
    }

    public void resume() {
        this.pauseCascade = false;
        timeLimit = originalTimeLimit;
        if(fireAdLoadedLater){
            listener.adLoaded(loadedAd);
            fireAdLoadedLater = false;
        }
    }

    @Override
    public void reset(){
        loadedAdIndex = networkAdsList.size();
    }

    @Override
    public void addListener(CascadeListener cascadeListener) {
        this.listener = cascadeListener;
    }

    @Override
    public void adFailedToLoad() {
        currentAdIndex++;
        if(currentAdIndex == loadedAdIndex || currentAdIndex == networkAdsList.size()){
            currentAdIndex=0;
            waitAndLoadAgain();
        }else{
            loadAd(activity); //load next ad
        }
    }

    @Override
    public void adLoaded(NetworkAd ad) {
        if(!pauseCascade) {
            listener.adLoaded(ad);
        }else {
            fireAdLoadedLater = true;
            loadedAd = ad;
        }
        loadedAdIndex = currentAdIndex;
        currentAdIndex = 0;
        waitAndLoadAgain();
    }
}
