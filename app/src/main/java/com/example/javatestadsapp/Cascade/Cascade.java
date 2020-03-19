package com.example.javatestadsapp.Cascade;

import android.app.Activity;

import java.util.LinkedHashMap;

public interface Cascade {

    // Create a HashMap object for Tags
    LinkedHashMap<String, String> poolTags = new LinkedHashMap<String, String>();

    //void initialize(LinkedHashMap poolTags);

    void execute(LinkedHashMap poolTags, Activity act);


}
