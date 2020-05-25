package com.example.cleveradsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.Serializable;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private CleverAdsPlugin cleverAdsPlugin;
    private CleverAdsPluginConfiguration cleverAdsConfig;
    private LinearLayout adContainer;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adContainer = findViewById(R.id.banner_container);
        if(cleverAdsPlugin == null) {
            cleverAdsConfig = new CleverAdsPluginConfiguration();
            cleverAdsPlugin = new CleverAdsPlugin(cleverAdsConfig.getstandardPoolTags(),
                    cleverAdsConfig.getAdType(), cleverAdsConfig.getAdWaitTimeLimit(), this);
        }
        registerActivityLifecycleCallbacks(ActivityHolder.getInstance());
        registerActivityLifecycleCallbacks(cleverAdsPlugin);
    }

    public void goToBannerActivity(View view) {
        //cleverAdsPlugin.showStandardAd();
        Intent intent = new Intent(this, BannerActivity.class);
        //intent.putExtra("clever_ads_plugin", cleverAdsPlugin);
        startActivity(intent);
    }

    public void onSwitchEnableStandardClicked(View view) {
        //Get reference of TextView from XML layout
        //TextView tView = findViewById(R.id.switchStandard);

        //Is the switch on?
        boolean on = ((Switch) view).isChecked();

        if(on) {
            //tView.setText("Enable Standard ON");
            cleverAdsPlugin.enableStandardAd(adContainer);
        } else {
            //tView.setText("Enable Standard OFF");
            cleverAdsPlugin.disableStandardAd();
        }
    }

    public void onSwitchContainerClicked(View view) {
        //Get reference of TextView from XML layout
        TextView tView = findViewById(R.id.switchContainer);

        //Is the switch on?
        boolean on = ((Switch) view).isChecked();

        if(on) {
            tView.setText("Container Appeared");
            cleverAdsPlugin.standardController.onContainerAppeared((LinearLayout) findViewById(R.id.banner_container));
        } else {
            tView.setText("Container Dissapeared");
            cleverAdsPlugin.standardController.onContainerDisappeared();
        }
    }

    public void showStandardAd(View view) {
        cleverAdsPlugin.standardController.onContainerAppeared((LinearLayout) findViewById(R.id.banner_container));
    }

    public void showInterstitialAd(View view) {
        cleverAdsPlugin.showInsterstitialAd();
    }
}
