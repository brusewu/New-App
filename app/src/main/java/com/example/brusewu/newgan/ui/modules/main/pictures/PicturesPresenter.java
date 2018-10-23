package com.example.brusewu.newgan.ui.modules.main.pictures;

import com.example.brusewu.newgan.data.model.BaseResponse;
import com.example.brusewu.newgan.data.model.Ganhuo;
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

public class PicturesPresenter extends BasePresenter<PicturesContract.View>
        implements PicturesContract.Presenter {

    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public PicturesPresenter(DataManager dataManager){
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
    public void getPictures(String category,final int page){
        Observable<BaseResponse<Ganhuo>> observable = dataManager.getGanHuo(category, page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<Ganhuo>, List<Ganhuo>>() {
                    @Override
                    public List<Ganhuo> apply(@NonNull BaseResponse<Ganhuo> ganhuoBaseResponse)
                        throws Exception{
                        return ganhuoBaseResponse.getResults();
                    }
                }).subscribe(new Observer<List<Ganhuo>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;

            }

            @Override
            public void onNext(@NonNull List<Ganhuo> ganhuoList) {
                getView().showList(ganhuoList,page);

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



















