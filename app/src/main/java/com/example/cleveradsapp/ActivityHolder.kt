package com.example.cleveradsapp

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log

class ActivityHolder private constructor() : ActivityLifecycleCallbacks {

    private var currentActivity: Activity? = null
    private var cleverAdsPlugin: CleverAdsPlugin? = null

    init {
        Log.d("TestAds_ActivityHolder", "Instance of ActivityHolder created")
    }

    fun setCleverAdsPlugin(cleverAdsPlugin: CleverAdsPlugin?) {
        this.cleverAdsPlugin = cleverAdsPlugin
    }

    fun getCurrentActivity(): Activity? {
        return activityHolder_instance?.currentActivity
    }

    fun setCurrentActivity(currentActivity: Activity?) {
        activityHolder_instance?.currentActivity = currentActivity
    }

    override fun onActivityResumed(activity: Activity) {
        activityHolder_instance?.currentActivity = activity
        Log.d("TestAds_ActivityHolder", "onActivityResumed: $activity")
        cleverAdsPlugin?.onActivityResume()
    }

    override fun onActivityPaused(activity: Activity) {
//        Log.d("TestAds_ActivityHolder", "onActivityPaused: " + activity.toString());
        cleverAdsPlugin?.onActivityPause()
    }

    override fun onActivityStarted(activity: Activity) {
//        Log.d("TestAds_ActivityHolder", "onActivityStarted: " + activity.toString());
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//        Log.d("TestAds_ActivityHolder", "onActivityCreated: " + activity.toString());
    }

    override fun onActivityStopped(activity: Activity) {
//        Log.d("TestAds_ActivityHolder", "onActivityStopped: " + activity.toString());
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}

    companion object {
        private var activityHolder_instance: ActivityHolder? = null
        @JvmStatic
        val instance: ActivityHolder?
            get() {
                if (activityHolder_instance == null) {
                    activityHolder_instance = ActivityHolder()
                }
                return activityHolder_instance
            }
    }
}