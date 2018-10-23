package com.example.brusewu.newgan.ui.modules.main.xiandu.list;

import com.example.brusewu.newgan.data.model.XianDu;
import com.example.brusewu.newgan.ui.base.BaseContract;

import java.util.List;

public interface XianDuListContract {
    interface View extends BaseContract.View{
        void showXianDu(List<XianDu> dataList, int page);
    }

    interface Presenter extends BaseContract.Presenter<XianDuListContract.View>{
        void getXianDu(String categoryId, int page);
    }

}
















