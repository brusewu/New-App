package com.example.brusewu.newgan.data.source;


import android.preference.PreferenceManager;

import com.example.brusewu.newgan.data.model.BaseResponse;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.data.model.XianDu;
import com.example.brusewu.newgan.data.model.XianDuCategory;
import com.example.brusewu.newgan.data.model.XianDuSubCategory;
import com.example.brusewu.newgan.data.source.local.PreferencesHelper;
import com.example.brusewu.newgan.data.source.remote.GankIOService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DataManager {

    private GankIOService gankIOService;
    private PreferencesHelper preferencesHelper;

    @Inject
    public DataManager(GankIOService gankIOService,PreferencesHelper preferencesHelper){
        this.gankIOService = gankIOService;
        this.preferencesHelper = preferencesHelper;
    }

    public Observable<BaseResponse<Ganhuo>> getGanHuo(String category, int page){
        return gankIOService.getGanHuo(category, page);
    }

    public Observable<BaseResponse<XianDuCategory>> getXianDuCategories(){
        return gankIOService.getXianDuCategories();
    }
    public Observable<BaseResponse<XianDuSubCategory>> getXianDuSubCategories(String enName){
        return gankIOService.getXianDuSubCategories(enName);
    }

    public Observable<BaseResponse<XianDu>> getXianDu(String categoryId,int page){
        return gankIOService.getXianDu(categoryId, page);
    }

    public Observable<BaseResponse<Ganhuo>> searchGanHuo(int page){
        return gankIOService.searchGanHuo(page);
    }

    public PreferencesHelper getPreferencesHelper(){
        return preferencesHelper;
    }


}
