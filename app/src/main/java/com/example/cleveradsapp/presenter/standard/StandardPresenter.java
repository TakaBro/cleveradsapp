package com.example.cleveradsapp.presenter.standard;

import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.example.cleveradsapp.presenter.Presenter;
import com.example.cleveradsapp.presenter.PresenterListener;

public class StandardPresenter implements Presenter, NetworkAdPresenterListener {

    PresenterListener listener;

    @Override
    public void showAd(NetworkAd ad) {

    }

    @Override
    public void adOpened() {

    }

    @Override
    public void adClosed() {

    }

    public void addListener(PresenterListener listener) {
        this.listener = listener;
    }
}
