package com.example.brusewu.newgan.deal.module;

import android.app.Application;
import android.content.Context;

import com.example.brusewu.newgan.NewGan;
import com.example.brusewu.newgan.data.source.remote.GankIOService;
import com.example.brusewu.newgan.deal.qualifier.ApplicationContext;
import com.example.brusewu.newgan.utils.Constans;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private NewGan application;

    public ApplicationModule(NewGan application){
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()                  //bruse add
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GankIOService provideGankIOService(Retrofit retrofit){
        return retrofit.create(GankIOService.class);
    }
}
