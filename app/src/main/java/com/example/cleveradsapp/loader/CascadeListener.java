package com.example.cleveradsapp.loader;

import com.example.cleveradsapp.networkAd.NetworkAd;

public interface CascadeListener {

    void adLoaded(NetworkAd ad);

    void adFailedToLoad();
}
