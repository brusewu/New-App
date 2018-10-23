package com.example.brusewu.newgan.ui.modules.settings;

import com.example.brusewu.newgan.data.source.DataManager;
import com.example.brusewu.newgan.ui.base.BasePresenter;

import javax.inject.Inject;

public class SettingsPresenter extends BasePresenter<SettingsContract.View>
        implements SettingsContract.Presenter {

    private DataManager dataManager;

    @Inject
    public SettingsPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public String getLanguageEntriesValue(String key){
        return dataManager.getPreferencesHelper().getStringPrefValue(key);
    }
}
