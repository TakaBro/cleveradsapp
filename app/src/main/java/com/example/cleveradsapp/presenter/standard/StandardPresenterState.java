package com.example.cleveradsapp.presenter.standard;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public enum StandardPresenterState {
    DISABLED_PRESENTING {
        @Override
        void enable() {
            Log.d("TestAds_DISABLED", "enable");
            resumePresentation();
        }

        @Override
        void disable() {

        }

        @Override
        void startPresentation() {

        }

        @Override
        void resumePresentation() {
            Log.d("TestAds_DISABLED", "resume ad presentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
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
        void startPresentation() {
            //Log.d("TestAds_DISABLED", "startPresentation");
        }

        @Override
        void resumePresentation() {

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
        void startPresentation() {
            Log.d("TestAds_BLOCKED", "startPresentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
            updateAdView();
            startAdPresentationTimer();
            presenter.listener.onAdPresentationStarted();
        }

        @Override
        void resumePresentation() {
            Log.d("TestAds_BLOCKED", "resume ad presentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
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
        void startPresentation() {
            Log.d("TestAds_PRESENTING", "startPresentation");
            updateAdView();
            startAdPresentationTimer();
            presenter.listener.onAdPresentationStarted();
        }

        @Override
        void resumePresentation() {

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
        presenter.adContainer.removeAllViews();
        presenter.adContainer.addView(presenter.ad.getView());
    }

    protected void startAdPresentationTimer() {
        presenter.startTime = System.nanoTime();
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
                presenter.listener.onAdPresentationFinished(presenter.adContainer);
            }
        };
        presenter.handler.postDelayed(presenter.r, timeToAdPresentationFinish);
    }

    public void saveAdPresentationTime() {
        presenter.elapsedTime = System.nanoTime() - presenter.startTime;
        presenter.remainingTime = TimeUnit.MILLISECONDS.toNanos(presenter.timeToAdPresentationFinish) - presenter.elapsedTime;
        /*Log.d(LOG_TAG, "startTime: " + presenter.startTime + " elapsedTime: " + presenter.elapsedTime +
              " timeToAdPresentationFinish: " + presenter.timeToAdPresentationFinish + " timeToAdPresentationFinish: " + presenter.remainingTime);*/
    }

    public void removeAdFromContainer() {
        presenter.adContainer.removeAllViews();
    }

    abstract void enable();
    abstract void disable();
    abstract void startPresentation();
    abstract void resumePresentation();
    abstract void pausePresentation();

/*    enum  PresentationState {
        RESUMING {
            @Override
            void addView() {
                presenter.adContainer.addView(presenter.currentAd.getView());
            }
        },
        EXPIRED {
            @Override
            void addView() {
                presenter.adContainer.addView(presenter.loadedAd.getView());
            }
        };

        StandardPresenter presenter;

        protected void SetPresenter(StandardPresenter presenter) {
            this.presenter = presenter;
        }

        abstract void addView();
    }*/
}
