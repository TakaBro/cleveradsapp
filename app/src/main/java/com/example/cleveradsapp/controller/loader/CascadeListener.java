package com.example.cleveradsapp.controller.loader;

import com.example.cleveradsapp.networkAd.NetworkAd;

public interface CascadeListener {

    void adLoaded(NetworkAd ad);

    void adFailedToLoad();
}
