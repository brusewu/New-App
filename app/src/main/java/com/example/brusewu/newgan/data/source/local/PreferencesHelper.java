package com.example.brusewu.newgan.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.brusewu.newgan.deal.qualifier.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {
    private SharedPreferences sharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public String getStringPrefValue(String key){
        return sharedPreferences.getString(key,"");
    }
}
