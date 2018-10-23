package com.example.brusewu.newgan.ui.modules.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.brusewu.newgan.R;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.ui.adapter.SearchResultAdapter;
import com.example.brusewu.newgan.ui.base.BaseActivity;
import com.example.brusewu.newgan.ui.listener.EndlessRecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;


import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Inject SearchPresenter presenter;

    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.clear_iv)
    ImageView clearInput;
    @BindView(R.id.search_results)
    RecyclerView resultsRecyclerView;
    @BindView(R.id.no_result_layout)
    FrameLayout noResultLayout;

    private SearchResultAdapter searchResultAdapter;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private String searchKeyword;

    @Override
    public int getLayout(){
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activityComponent.inject(this);

        if (presenter == null){
            return;
        }

        presenter.attachView(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        resultsRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycler_divider));
        resultsRecyclerView.addItemDecoration(itemDecoration);
        searchResultAdapter = new SearchResultAdapter();
        resultsRecyclerView.setAdapter(searchResultAdapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.search(searchInput.getText().toString(),page);
            }
        };
        resultsRecyclerView.addOnScrollListener(recyclerViewScrollListener);
    }

    @OnEditorAction(R.id.search_input)
    public boolean onEditor(){
        searchKeyword = searchInput.getText().toString();
        presenter.search(searchKeyword,1);
        return true;
    }

    @OnTextChanged(value = R.id.search_input,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChanged(Editable s){
        String text = s.toString();
        if (text.length() == 0){
            //hide the clear key
            clearInput.setVisibility(View.GONE);
        }else {
            //display the clear key
            clearInput.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.clear_iv)
    public void clearInput(){
        searchInput.setText("");
    }

    @Override
    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(searchInput.getWindowToken(),0);
        }
    }

    @Override
    public void showNoResult(){
        searchResultAdapter.clear();
        recyclerViewScrollListener.resetState();
        resultsRecyclerView.setVisibility(View.GONE);
        noResultLayout.setVisibility(View.VISIBLE);
    }
    @Override
    public void showResults(List<Ganhuo> resultItems,int page){
        resultsRecyclerView.setVisibility(View.VISIBLE);
        noResultLayout.setVisibility(View.GONE);
        if (page == 1){
            searchResultAdapter.setData(resultItems);
            searchResultAdapter.setSearchKeyword(searchKeyword);

        }else {
            searchResultAdapter.appendItems(resultItems);
        }
    }

}


















