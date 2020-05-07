package com.example.cleveradsapp.loader;

import com.example.cleveradsapp.networkAd.NetworkAd;

import java.util.ArrayList;

public interface Cascade {

    ArrayList<NetworkAd> networkAdsList = new ArrayList<NetworkAd>();

    void loadAd();

    void pause();

    void resume();

    void reset();

    void addListener(CascadeListener cascadeListener);
}
