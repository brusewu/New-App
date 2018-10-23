package com.example.brusewu.newgan.ui.modules.main.home.tabpage;

import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.ui.base.BaseContract;

import java.util.List;

public interface GanHuoPageContract {

    interface View extends BaseContract.View{
        void showList(List<Ganhuo>data,int page);
    }

    interface Presenter extends BaseContract.Presenter<GanHuoPageContract.View>{
        void getGanHuo(String category,int page);
    }
}
