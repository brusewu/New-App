package com.example.brusewu.newgan.ui.modules.main.home.tabpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.brusewu.newgan.R;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.ui.adapter.GanhuoAdapter;
import com.example.brusewu.newgan.ui.base.BaseFragment;
import com.example.brusewu.newgan.ui.listener.EndlessRecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class GanHuoPageFragment extends BaseFragment implements GanHuoPageContract.View {

    @Inject
    GanHuoPagePresenter presenter;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private GanhuoAdapter adapter;
    private String categoryName;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;

    public static GanHuoPageFragment newInstance(String tabTitle) {
        Bundle args = new Bundle();
        args.putString("TITLE", tabTitle);
        GanHuoPageFragment fragment = new GanHuoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.swipe_refresh_recyclerview;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter != null) {
            presenter.attachView(this);
        }

        String tabTitle = getArguments().getString("TITLE");
        categoryName = tabTitle.equals(getContext().getString(R.string.tab_title_all)) ? "all" : tabTitle;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.recycler_divider));
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new GanhuoAdapter();
        recyclerView.setAdapter(adapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                adapter.setLoading();
                presenter.getGanHuo(categoryName, page);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getGanHuo(categoryName, 1);
                recyclerViewScrollListener.resetState();
                adapter.clear();
                adapter.resetFooter();
            }
        });
        refreshLayout.setRefreshing(true);
        presenter.getGanHuo(categoryName, 1);
    }

    @Override
    public void showError(String message) {
        super.showError(message);
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        adapter.resetFooter();
    }

    @Override
    public void showList(List<Ganhuo> data, int page) {
        if (page == 1) {
            refreshLayout.setRefreshing(false);
            adapter.setData(data);
        } else {
            if (data == null || (data != null && data.size() == 0)) {
                adapter.setNoMoreHint();
            }
            adapter.appendItems(data);
        }
    }
}
