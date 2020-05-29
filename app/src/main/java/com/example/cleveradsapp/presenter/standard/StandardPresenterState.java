package com.example.cleveradsapp.presenter.standard;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cleveradsapp.networkAd.NetworkAd;

public enum StandardPresenterState {
    DISABLED_PRESENTING {
        @Override
        void enable() {
            Log.d("TestAds_DISABLED", "enable");
            resumePresentation(presenter.adContainer);
        }

        @Override
        void disable() {

        }

        @Override
        void startPresentation(NetworkAd ad, LinearLayout adContainer) {

        }

        @Override
        void resumePresentation(LinearLayout adContainer) {
            Log.d("TestAds_DISABLED", "resume ad presentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
            presenter.adContainer = adContainer;
            updateAdView();
            resumeAdPresentationTimer();
        }

        @Override
        void pausePresentation() {

        }
    },
    DISABLED_BLOCKED {
        @Override
        void enable() {
            Log.d("TestAds_DISABLED", "enable");
            presenter.currentState = StandardPresenterState.BLOCKED;
        }

        @Override
        void disable() {

        }

        @Override
        void startPresentation(NetworkAd ad, LinearLayout adContainer) {
            Log.d("TestAds_DISABLED", "can't start presentation");
        }

        @Override
        void resumePresentation(LinearLayout adContainer) {

        }

        @Override
        void pausePresentation() {

        }

    },
    BLOCKED {
        @Override
        void enable() {

        }

        @Override
        void disable() {
            Log.d("TestAds_BLOCKED", "disable");
            presenter.currentState = StandardPresenterState.DISABLED_BLOCKED;
        }

        @Override
        void startPresentation(NetworkAd ad, LinearLayout adContainer) {
            Log.d("TestAds_BLOCKED", "startPresentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
            presenter.adContainer = adContainer;
            presenter.presentationAd = ad;
            updateAdView();
            startAdPresentationTimer();
            presenter.listener.onAdPresentationStarted();
        }

        @Override
        void resumePresentation(LinearLayout adContainer) {
            Log.d("TestAds_BLOCKED", "resume ad presentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
            presenter.adContainer = adContainer;
            updateAdView();
            resumeAdPresentationTimer();
        }

        @Override
        void pausePresentation() {

        }

    },
    PRESENTING {
        @Override
        void enable() {

        }

        @Override
        void disable() {
            Log.d("TestAds_PRESENTING", "disable");
            presenter.currentState = StandardPresenterState.DISABLED_PRESENTING;
            presenter.handler.removeCallbacks(presenter.r);
            removeAdFromContainer();
        }

        @Override
        void startPresentation(NetworkAd ad, LinearLayout adContainer) {
            Log.d("TestAds_PRESENTING", "startPresentation");
            presenter.adContainer = adContainer;
            presenter.presentationAd = ad;
            updateAdView();
            startAdPresentationTimer();
            presenter.listener.onAdPresentationStarted();
        }

        @Override
        void resumePresentation(LinearLayout adContainer) {

        }

        @Override
        void pausePresentation() {
            Log.d("TestAds_PRESENTING", "pausePresentation");
            presenter.currentState = StandardPresenterState.BLOCKED;
            pauseAdPresentationTimer();
        }
    };

    protected String LOG_TAG = "TestAds_StandardPresenterState";
    StandardPresenter presenter;

    protected void setPresenter(StandardPresenter presenter) {
        this.presenter = presenter;
    }

    protected void updateAdView() {
        if (presenter.presentationAd.getView().getParent() != null) {
            Log.d("TestAds_updateAdView", "presenter.presentationAd.getView().getParent() != null");
            //The specified child already has a parent. You must call removeView() on the child's parent first.
            ((ViewGroup)presenter.presentationAd.getView().getParent()).removeView(presenter.presentationAd.getView());
        }
        Log.d("TestAds_updateAdView", "presenter.adContainer.addView(presenter.presentationAd.getView())");
        presenter.adContainer.addView(presenter.presentationAd.getView());
    }

    protected void startAdPresentationTimer() {
        presenter.startTime = System.currentTimeMillis();
        waitAdPresentationFinish(presenter.timeToAdPresentationFinish);
    }

    protected void pauseAdPresentationTimer() {
        presenter.handler.removeCallbacks(presenter.r);
        saveAdPresentationTime();
    }

    protected void resumeAdPresentationTimer() {
        Log.d(LOG_TAG, "remainingTime: " + presenter.remainingTime);
        waitAdPresentationFinish(presenter.remainingTime);
    }

    protected void waitAdPresentationFinish(long timeToAdPresentationFinish) {
        Log.d(LOG_TAG, "waiting to ad presentation finish...");
        presenter.r = new Runnable() {
            @Override
            public void run() {
                presenter.listener.onAdPresentationFinished(presenter.presentationAd, presenter.adContainer);
            }
        };
        presenter.handler.postDelayed(presenter.r, timeToAdPresentationFinish);
    }

    public void saveAdPresentationTime() {
        presenter.elapsedTime = System.currentTimeMillis() - presenter.startTime;
        presenter.remainingTime = presenter.timeToAdPresentationFinish - presenter.elapsedTime;
        /*Log.d(LOG_TAG, "startTime: " + presenter.startTime + " elapsedTime: " + presenter.elapsedTime +
              " timeToAdPresentationFinish: " + presenter.timeToAdPresentationFinish + " timeToAdPresentationFinish: " + presenter.remainingTime);*/
    }

    public void removeAdFromContainer() {
        presenter.adContainer.removeAllViews();
    }

    abstract void enable();
    abstract void disable();
    abstract void startPresentation(NetworkAd ad, LinearLayout adContainer);
    abstract void resumePresentation(LinearLayout adContainer);
    abstract void pausePresentation();
}
