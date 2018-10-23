package com.example.brusewu.newgan.ui.modules.search;

import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.ui.base.BaseContract;

import java.util.List;

public interface SearchContract {
    interface View extends BaseContract.View{
        void closeKeyboard();
        void showNoResult();
        void showResults(List<Ganhuo> resultItems,int pages);
    }

    interface Presenter extends BaseContract.Presenter<View>{
        void search(String keyword,int pages);
    }
}
