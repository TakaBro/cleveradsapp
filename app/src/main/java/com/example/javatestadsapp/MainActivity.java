package com.example.javatestadsapp;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import static java.lang.Thread.sleep;
import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity /*implements Runnable*/ {

    private String TAG = "TestAds";
    //AppLovinInterstitialAdDialog interstitialAd;
    /*private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterstitialAdLoaded;*/

    // Create a HashMap object for Tags
    LinkedHashMap<String, String> poolTags = new LinkedHashMap<String, String>();
    private int i = 0;
    private int selectedAd;
    Handler handler = new Handler();
    private long timeLimit = 3000;
    private boolean printSeparation = false;

    private Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller.setup(1);
        controller.initializeSDKs(this);
        //mInterstitialAdLoaded = new InterstitialAd(this);

        // Load arraylist of tags for cascade and run it
        controller.startCascate(poolTags, this);
        //controller.runCascate(poolTags, this);

        /*controller.adMobAdNetwork.createAndLoadAdMobInterstitialAd(
                poolTags.keySet().toArray()[4].toString(), this);*/
    }

    /*public void runCascate(LinkedHashMap tags){
        createAndLoadAdMobInterstitialAd(tags.keySet().toArray()[i].toString());
        //appLovinAdNet.createAndLoadAppLovinInterstitialAd(this);
        Log.d(TAG, "loading Ad ...");
    }*/

    /*public void createAndLoadAdMobInterstitialAd(String tag){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(tag);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        listenerAdMobInterstitial();
    }*/

    /*public void listenerAdMobInterstitial() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAdLoaded = mInterstitialAd;
                Log.d(TAG, "AdMob Interstitial LOADED " + poolTags.get(i)); // Code to be executed when an ad finishes loading.
                Log.d(TAG, "--------------------------------------");
                //mInterstitialAd.show();
                selectedAd = i; i = 0;
                waitToCascateAgain();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(TAG, "adMobInterstitial FAILED TO LOAD " + poolTags.get(i) + " i:" + i); // Code to be executed when an ad request fails.
                i++;
                if(i==selectedAd){
                    waitToCascateAgain();
                    printSeparation = true;
                    i=0;
                }else{
                    createAndLoadAdMobInterstitialAd(poolTags.keySet().toArray()[i].toString());
                }

                switch (errorCode)
                {
                    case 0:
                        Log.d(TAG, "ERROR_CODE_INTERNAL_ERROR");
                    case 1:
                        Log.d(TAG, "ERROR_CODE_INVALID_REQUEST");
                    case 2:
                        Log.d(TAG, "ERROR_CODE_NETWORK_ERROR");
                    case 3:
                        Log.d(TAG, "ERROR_CODE_NO_FILL");
                    default:
                        if(printSeparation){
                            Log.d(TAG, "--------------------------------------");
                            printSeparation = false;
                        }
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }*/

    /*public void waitToCascateAgain(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                createAndLoadAdMobInterstitialAd(poolTags.keySet().toArray()[0].toString());
                System.out.println("TestAds Loading next ad");
            }
        };
        handler.postDelayed(r, timeLimit);
        //waitCascadeThread.run();
    }*/

    /*@Override
    public void run() {
        try {
            Thread.sleep(timeLimit);
            createAndLoadAdMobInterstitialAd(poolTags.keySet().toArray()[0].toString());
            System.out.println("TestAds Loading next ad");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("TestAds EXCEPTION");
        }
    }*/

    public void showInterstitialAd(View view)
    {
        //ADMOB, ADMOB mediation, UNITYADS and APPLOVIN
        if (controller.adMobAdNetwork.mInterstitialAdLoaded.isLoaded()) {
            controller.adMobAdNetwork.mInterstitialAdLoaded.show();
            selectedAd = poolTags.size();
        } else if(UnityAds.isReady (controller.unityAdNet.placementId)) {
            controller.unityAdNet.showUnityAdInterstitial(this);
        /*}else if(appLovinAdNet.loadedAd.getAdIdNumber() != NULL){
            appLovinAdNet.interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );
            //appLovinAdNet.interstitialAd.setAdDisplayListener( );
            //appLovinAdNet.interstitialAd.setAdClickListener( );
            appLovinAdNet.interstitialAd.show();*/
        }else {
            Log.d("TestAdsApp", "The interstitial wasn't loaded yet.");
        }
    }
}
