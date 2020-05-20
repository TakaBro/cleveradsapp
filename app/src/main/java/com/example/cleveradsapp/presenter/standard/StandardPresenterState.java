package com.example.cleveradsapp.presenter.standard;

import android.util.Log;

public enum StandardPresenterState {

/*    ENABLE {
        @Override
        void show() {
            Log.d(LOG_TAG, "show ad");
            presenter.currentState = StandardPresenterState.RUNNING;
            presenter.adContainer.removeAllViews();
            presenter.adContainer.addView(presenter.ad.getView());
            runAdPresentationTimer();
        }

        @Override
        void hide(String aParameter) {

        }
        @Override
        void pause(String aParameter) {

        }
        @Override
        void resume(String aParameter) {

        }
    },*/
    BLOCKED {
        @Override
        void show() {
            Log.d(LOG_TAG, "show ad");
            presenter.currentState = StandardPresenterState.PRESENTING;
            presenter.adContainer.removeAllViews();
            presenter.adContainer.addView(presenter.currentAd.getView());
            runAdPresentationTimer();
        }

        @Override
        void hide() {

        }

        @Override
        void pause() {

        }

        @Override
        void resume() {

        }
    },
    PRESENTING {
        @Override
        void show() {

        }

        @Override
        void hide() {
            presenter.currentState = StandardPresenterState.BLOCKED;
            saveAdPresentationTime();
            //removeAdFromContainer();
        }

        @Override
        void pause() {
/*            presenter.currentState = StandardPresenterState.BLOCKED;
            saveAdPresentationTime();
            removeAdFromContainer();*/
        }

        @Override
        void resume() {

        }
    },
    PAUSED {
        @Override
        void show() {

        }

        @Override
        void hide() {

        }

        @Override
        void pause() {

        }

        @Override
        void resume() {
            presenter.currentState = StandardPresenterState.PRESENTING;
        }
    };

    protected String LOG_TAG = "TestAds_StandardPresenterState";
    StandardPresenter presenter;

    protected void GetPresenter(StandardPresenter presenter) {
        this.presenter = presenter;
    }

    protected void runAdPresentationTimer() {
        presenter.startTime = System.nanoTime();
        checkAdPresentationTime();
    }

    protected void checkAdPresentationTime() {
        if(presenter.remainingTime != 0) {
            waitAdPresentationFinish(presenter.remainingTime);
            presenter.remainingTime = 0;
        } else {
            waitAdPresentationFinish(presenter.timeToAdPresentationFinish);
        }
    }

    protected void waitAdPresentationFinish(long timeToRefreshAdParameter) {
        Log.d(LOG_TAG, "waiting to ad presentation finish...");
        presenter.r = new Runnable() {
            @Override
            public void run() {
                presenter.listener.onAdPresentationFinished(presenter.adContainer);
                //presenter.currentAd = presenter.loadedAd;
            }
        };
        presenter.handler.postDelayed(presenter.r, timeToRefreshAdParameter);
    }

    public void saveAdPresentationTime() {
        /*elapsedTime = System.nanoTime() - startTime;
        remainingTime = timeToAdPresentationFinish - elapsedTime;*/
    }

    abstract void show();
    abstract void hide();
    abstract void pause();
    abstract void resume();
}
