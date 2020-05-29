package com.example.cleveradsapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cleveradsapp.ActivityHolder.Companion.instance

class BannerActivity : AppCompatActivity() {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        registerActivityLifecycleCallbacks(instance!!)
    }

    fun goToAnotherBannerActivity(view: View?) {
        val intent = Intent(this, AnotherBannerActivity::class.java)
        startActivity(intent)
    }
}