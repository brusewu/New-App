package com.example.brusewu.newgan;

import android.app.Application;

import com.example.brusewu.newgan.deal.component.ApplicationComponent;
import com.example.brusewu.newgan.deal.component.DaggerApplicationComponent;
import com.example.brusewu.newgan.deal.module.ApplicationModule;

public class NewGan extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return getApplicationComponent();
    }



}
