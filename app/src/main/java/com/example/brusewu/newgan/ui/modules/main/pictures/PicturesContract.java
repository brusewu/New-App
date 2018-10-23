package com.example.brusewu.newgan.ui.modules.main.pictures;

import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.ui.base.BaseContract;

import java.util.List;

public class PicturesContract {

    interface View extends BaseContract.View{
        void showList(List<Ganhuo> data,int page);
    }

    interface Presenter extends BaseContract.Presenter<PicturesContract.View>{
        void getPictures(String category,int page);
    }
}
