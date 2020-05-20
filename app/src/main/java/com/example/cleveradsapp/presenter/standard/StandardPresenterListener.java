package com.example.cleveradsapp.presenter.standard;

import android.widget.LinearLayout;

import com.example.cleveradsapp.presenter.PresenterListener;

public interface StandardPresenterListener extends PresenterListener {

    void onAdPresentationFinished(LinearLayout adContainer);
}
