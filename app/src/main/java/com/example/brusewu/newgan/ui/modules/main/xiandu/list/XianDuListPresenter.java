package com.example.brusewu.newgan.ui.modules.main.xiandu.list;

import com.example.brusewu.newgan.data.model.BaseResponse;
import com.example.brusewu.newgan.data.model.XianDu;
import com.example.brusewu.newgan.data.source.DataManager;
import com.example.brusewu.newgan.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class XianDuListPresenter extends BasePresenter<XianDuListContract.View>
        implements XianDuListContract.Presenter {

    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public XianDuListPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public void detachView(){
        super.detachView();
        if (disposable != null){
            disposable.dispose();
        }
    }

    @Override
    public void getXianDu(String categoryId,final int page){

        Observable<BaseResponse<XianDu>> observable = dataManager.getXianDu(categoryId,page);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<XianDu>, List<XianDu>>() {
                    @Override
                    public List<XianDu> apply(@NonNull BaseResponse<XianDu> xianDuBaseResponse)
                        throws Exception{
                        return xianDuBaseResponse.getResults();
                    }
                }).subscribe(new Observer<List<XianDu>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<XianDu> xianDusList) {
                getView().showXianDu(xianDusList,page);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


}


















