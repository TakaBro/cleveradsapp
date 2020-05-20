package com.example.cleveradsapp.networkAd;

import android.view.View;

public interface NetworkAd {

    void request();

    void show();

    String getTag();

    String getNet();

    View getView();
}
