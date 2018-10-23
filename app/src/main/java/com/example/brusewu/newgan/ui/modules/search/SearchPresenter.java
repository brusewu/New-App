package com.example.brusewu.newgan.ui.modules.search;



import com.example.brusewu.newgan.data.model.BaseResponse;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.data.source.DataManager;
import com.example.brusewu.newgan.ui.base.BasePresenter;
import com.example.brusewu.newgan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;



import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.View>
        implements SearchContract.Presenter {

    private DataManager dataManager;

    @Inject
    public SearchPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public void search(final String keyword,final int page){
        if (!StringUtils.isEmpty(keyword)){
            getView().closeKeyboard();
            final List<Ganhuo> ganHuoList = new ArrayList<>();
            Observable<BaseResponse<Ganhuo>> observable = dataManager.searchGanHuo(page);
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
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
            }).filter(new Predicate<Ganhuo>() {
                @Override
                public boolean test(@NonNull Ganhuo ganHuo) throws Exception {
                    return ganHuo.getDesc().toLowerCase().contains(keyword.toLowerCase())
                            || ganHuo.getType().toLowerCase().contains(keyword.toLowerCase());
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
                    if (ganHuoList.size() == 0 && page == 1) {
                        getView().showNoResult();
                    } else {
                        getView().showResults(ganHuoList, page);
                    }
                }
            });

        }
    }

}
