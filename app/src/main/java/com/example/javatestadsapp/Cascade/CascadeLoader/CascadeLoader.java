package com.example.javatestadsapp.Cascade.CascadeLoader;

import android.app.Activity;

import com.example.javatestadsapp.Cascade.Cascade;
import com.example.javatestadsapp.Cascade.CascadeListener.CascadeListener;
import com.example.javatestadsapp.Cascade.CrazyCascade.CrazyCascade;
import com.example.javatestadsapp.Cascade.SimpleCascade.SimpleCascade;

import java.util.LinkedHashMap;

public class CascadeLoader implements CascadeListener {

    private int cascadeType;
    Cascade cascade;

    public CascadeLoader(int type){
        cascadeType = type;

        if(cascadeType==0){
            cascade = new SimpleCascade();
        }else {
            cascade = new CrazyCascade();
        }
    }

    public void loadAd(LinkedHashMap poolTags, Activity act){
        //cascade.initialize(poolTags);
        initializeCascade(/*poolTags*/);
        cascade.execute(poolTags, act);
    }

    public void initializeCascade(/*LinkedHashMap poolTags*/){
        // Add keys and values (tag, net)
        //cascade.poolTags.put("ca-app-pub-5073389895475742/6911676682", "AdMob");            //no fill
        //cascade.poolTags.put("ca-app-pub-5073389895475742/1333192516", "AdMob");            //no fill
        cascade.poolTags.put("ca-app-pub-3940256099942544/1033173712", "AdMob");              //test
        cascade.poolTags.put("1675872-video", "UnityAds");                                  //unityads
        cascade.poolTags.put("ca-app-pub-5073389895475742/8102108072", "AdMob-AppLovin");   //mediation

        /*for (String i : poolTags.keySet()) {
                System.out.println("tag: " + i + "network: " + poolTags.get(i));
        }*/
    }

    @Override
    public void adLoaded() {

    }

    @Override
    public void adFailedToLoad() {

    }
}
