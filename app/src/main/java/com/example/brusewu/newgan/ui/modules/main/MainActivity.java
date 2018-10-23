package com.example.brusewu.newgan.ui.modules.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.example.brusewu.newgan.R;
import com.example.brusewu.newgan.ui.base.BaseActivity;
import com.example.brusewu.newgan.ui.modules.main.home.HomeFragment;
import com.example.brusewu.newgan.ui.modules.main.more.MoreFragment;
import com.example.brusewu.newgan.ui.modules.main.pictures.PicturesFragment;
import com.example.brusewu.newgan.ui.modules.main.xiandu.XianDuFragment;
import com.example.brusewu.newgan.ui.modules.search.SearchActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.bottom_nav)
    BottomNavigationViewEx bottomNav;

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 依赖注入
        activityComponent.inject(this);

        if (presenter != null) {
            presenter.attachView(this);
        }

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        fragmentManager = getSupportFragmentManager();
        createFragments();
        selectFragment(0);
        setupBottomNavigationView();
    }
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    public void setupBottomNavigationView() {
        bottomNav.enableAnimation(false);
        bottomNav.enableShiftingMode(false);
        bottomNav.enableItemShiftingMode(false);
        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        boolean enableElevation = false;
                        switch (item.getItemId()) {
                            case R.id.home:
                                enableElevation = false;
                                selectFragment(0);
                                break;
                            case R.id.xiandu:
                                enableElevation = false;
                                selectFragment(1);
                                break;
                            case R.id.pictures:
                                enableElevation = true;
                                selectFragment(2);
                                break;
                            case R.id.more:
                                enableElevation = true;
                                selectFragment(3);
                                break;
                        }
                        enableAppBarElevation(enableElevation);
                        return true;

                    }
                });
    }

    /**
     * 创建 fragment
     */
    public void createFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentTransaction.add(R.id.fragment_container, homeFragment);

        XianDuFragment xianDuFragment = new XianDuFragment();
        fragmentList.add(xianDuFragment);
        fragmentTransaction.add(R.id.fragment_container, xianDuFragment);

        PicturesFragment picturesFragment = new PicturesFragment();
        fragmentList.add(picturesFragment);
        fragmentTransaction.add(R.id.fragment_container, picturesFragment);

        MoreFragment moreFragment = new MoreFragment();
        fragmentList.add(moreFragment);
        fragmentTransaction.add(R.id.fragment_container, moreFragment);

        fragmentTransaction.commit();
    }

    /**
     * 根据给定下标选中对应的 fragment
     * @param index fragment 在列表中的下标
     */
    public void selectFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == index) {
                fragmentTransaction.show(fragmentList.get(i));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }
}
