package com.example.cleveradsapp

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cleveradsapp.ActivityHolder.Companion.instance

class AnotherBannerActivity : AppCompatActivity() {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another_banner)
        registerActivityLifecycleCallbacks(instance!!)
    }
}