package com.example.brusewu.newgan.ui.modules.main.xiandu;

import com.example.brusewu.newgan.data.model.XianDuCategory;
import com.example.brusewu.newgan.ui.base.BaseContract;

import java.util.List;

public interface XianDuContract {

    interface View extends BaseContract.View{
        void setupTab(List<XianDuCategory> data);
    }

    interface Presenter extends BaseContract.Presenter<XianDuContract.View>{
        void getCategories();
    }
}
