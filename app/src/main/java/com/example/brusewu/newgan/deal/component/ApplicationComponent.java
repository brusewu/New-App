package com.example.brusewu.newgan.deal.component;

import android.app.Application;

import com.example.brusewu.newgan.data.source.DataManager;
import com.example.brusewu.newgan.deal.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application getApplication();
    DataManager getDataManager();

}
