package com.example.cleveradsapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityHolder implements Application.ActivityLifecycleCallbacks {

    private static ActivityHolder activityHolder_instance = null;
    private Activity currentActivity;

    private ActivityHolder() {
        Log.d("TestAds_ActivityHolder", "Instance of ActivityHolder created");
    }

    public static ActivityHolder getInstance() {
        if (activityHolder_instance == null) {
            activityHolder_instance = new ActivityHolder();
        }
        return activityHolder_instance;
    }

    public Activity getCurrentActivity() {
        return activityHolder_instance.currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        activityHolder_instance.currentActivity = currentActivity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        activityHolder_instance.currentActivity = activity;
        /*if(currentActivity.findViewById(R.id.banner_container) != null) {
            Log.d("TestAds_ActivityHolder", "This Activity contains banner_container!");
        }
        Log.d("TestAds_ActivityHolder", "onActivityResumed: " + activity.toString());*/
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
//        Log.d("TestAds_ActivityHolder", "onActivityStarted: " + activity.toString());
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
//        Log.d("TestAds_ActivityHolder", "onActivityCreated: " + activity.toString());
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
//        Log.d("TestAds_ActivityHolder", "onActivityPaused: " + activity.toString());
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
//        Log.d("TestAds_ActivityHolder", "onActivityStopped: " + activity.toString());
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
