package com.example.brusewu.newgan.ui.modules.main.home.tabpage;





import com.example.brusewu.newgan.data.model.BaseResponse;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.data.source.DataManager;
import com.example.brusewu.newgan.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class GanHuoPagePresenter extends BasePresenter<GanHuoPageContract.View>
        implements GanHuoPageContract.Presenter {

    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public GanHuoPagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getGanHuo(String category, final int page) {
        final List<Ganhuo> ganHuoList = new ArrayList<>();
        Observable<BaseResponse<Ganhuo>> observable = dataManager.getGanHuo(category, page);
        disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Function<BaseResponse<Ganhuo>, ObservableSource<Ganhuo>>() {
                    @Override
                    public ObservableSource<Ganhuo> apply(@NonNull BaseResponse<Ganhuo> ganHuoBaseResponse)
                            throws Exception {
                        return Observable.fromIterable(ganHuoBaseResponse.getResults());
                    }
                }).filter(new Predicate<Ganhuo>() {
                    @Override
                    public boolean test(@NonNull Ganhuo ganHuo) throws Exception {
                        return !ganHuo.getType().equals("福利");
                    }
                }).subscribe(new Consumer<Ganhuo>() {
                    @Override
                    public void accept(Ganhuo ganHuo) throws Exception {
                        ganHuoList.add(ganHuo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().showError(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        getView().showList(ganHuoList, page);
                    }
                });
    }
}
