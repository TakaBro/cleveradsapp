package com.example.cleveradsapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cleveradsapp.ActivityHolder.Companion.instance

class MainActivity : AppCompatActivity() {
    private var cleverAdsPlugin: CleverAdsPlugin? = null
    private var cleverAdsConfig: CleverAdsPluginConfiguration? = null
    private var adContainer: LinearLayout? = null

    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adContainer = findViewById(R.id.banner_container)
        if (cleverAdsPlugin == null) {
            cleverAdsConfig = CleverAdsPluginConfiguration()
            cleverAdsPlugin = CleverAdsPlugin(cleverAdsConfig!!.standardPoolTags,
                    cleverAdsConfig!!.interstitialPoolTags, cleverAdsConfig!!.adType,
                    cleverAdsConfig!!.adWaitTimeLimit, this)
        }
        registerActivityLifecycleCallbacks(instance!!)
    }

    fun goToBannerActivity(view: View?) {
        val intent = Intent(this, BannerActivity::class.java)
        startActivity(intent)
    }

    fun onSwitchEnableStandardClicked(view: View) {
        //Is the switch on?
        val on = (view as Switch).isChecked
        if (on) {
            cleverAdsPlugin!!.enableStandardAd(adContainer)
        } else {
            cleverAdsPlugin!!.disableStandardAd()
        }
    }

    fun showStandardAd(view: View?) {
        cleverAdsPlugin!!.standardController.onContainerAppeared(findViewById<View>(R.id.banner_container) as LinearLayout)
    }

    fun showInterstitialAd(view: View?) {
        cleverAdsPlugin!!.showInsterstitialAd()
    }
}