package com.example.brusewu.newgan.ui.modules.main;

import com.example.brusewu.newgan.ui.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View{}

    interface Presenter extends BaseContract.Presenter<MainContract.View>{}
}
