package com.example.cleveradsapp.presenter.standard;

import android.widget.LinearLayout;

import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.presenter.Presenter;

public interface StandardPresenterInterface extends Presenter {

    void showAd(NetworkAd ad, LinearLayout adContainer);

}
