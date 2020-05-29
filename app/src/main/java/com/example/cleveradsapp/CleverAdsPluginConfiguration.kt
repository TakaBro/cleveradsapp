package com.example.cleveradsapp

import java.util.*

class CleverAdsPluginConfiguration {

    val standardPoolTags = LinkedHashMap<String, String>() // Create a HashMap object for Standard Tags
    val interstitialPoolTags = LinkedHashMap<String, String>() // Create a HashMap object for Interstital Tags
    val adWaitTimeLimit: Long = 5000
    var adType = 0

    init {
        fillPoolTags()
    }

    fun fillPoolTags() {
        fillInterstitialPoolTags()
        fillStandardPoolTags()
    }

    fun fillStandardPoolTags() {
        standardPoolTags["ca-app-pub-4452499906520493/1234567890"] = "AdMob" //error
        standardPoolTags["ca-app-pub-3940256099942544/6300978111"] = "AdMob" //test
        adType = 0
    }

    fun fillInterstitialPoolTags() {
        interstitialPoolTags["ca-app-pub-4452499906520493/1234567890"] = "AdMob" //error
        interstitialPoolTags["ca-app-pub-3940256099942544/1033173712"] = "AdMob" //test
        interstitialPoolTags["1675872-video"] = "UnityAds" //unityads
        interstitialPoolTags["ca-app-pub-5073389895475742/8102108072"] = "AdMob-AppLovin" //mediation
        adType = 1
    }
}