package com.example.brusewu.newgan.ui.modules.settings;

import com.example.brusewu.newgan.ui.base.BaseContract;

public interface SettingsContract {

    interface View extends BaseContract.View{}

    interface Presenter extends BaseContract.Presenter<View>{
        String getLanguageEntriesValue(String key);
    }
}
