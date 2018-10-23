package com.example.brusewu.newgan.ui.modules.main.xiandu.tabpage;

import com.example.brusewu.newgan.data.model.XianDuSubCategory;
import com.example.brusewu.newgan.ui.base.BaseContract;

import java.util.List;

public interface XianDuPageContract {

    interface View extends BaseContract.View{
        void showList(List<XianDuSubCategory> data);
    }

    interface Presenter extends BaseContract.Presenter<XianDuPageContract.View>{
        void getSubCategories(String parent);
    }
}
