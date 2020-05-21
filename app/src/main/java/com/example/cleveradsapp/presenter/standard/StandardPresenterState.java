package com.example.cleveradsapp.presenter.standard;

import android.util.Log;

public enum StandardPresenterState {

    DISABLED {
        @Override
        void enable() {
            presenter.currentState = StandardPresenterState.BLOCKED;
        }

        @Override
        void disable() {

        }

        @Override
        void startPresentation() {

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
            presenter.currentState = StandardPresenterState.DISABLED;
        }

        @Override
        void startPresentation() {
            Log.d(LOG_TAG, "start ad presentation");
            presenter.currentState = StandardPresenterState.PRESENTING;
            updateAdView();
            startAdPresentationTimer();
        }

        @Override
        void resumePresentation() {
            Log.d(LOG_TAG, "resume ad presentation");
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
            presenter.currentState = StandardPresenterState.DISABLED;
            saveAdPresentationTime();
            removeAdFromContainer();
        }

        @Override
        void startPresentation() {

        }

        @Override
        void resumePresentation() {

        }

        @Override
        void pausePresentation() {
            presenter.currentState = StandardPresenterState.BLOCKED;
            saveAdPresentationTime();
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

    protected void resumeAdPresentationTimer() {
        presenter.startTime = System.nanoTime();
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
        presenter.remainingTime = presenter.timeToAdPresentationFinish - presenter.elapsedTime;
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
