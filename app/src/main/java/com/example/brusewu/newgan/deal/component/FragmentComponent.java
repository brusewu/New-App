package com.example.brusewu.newgan.deal.component;

import com.example.brusewu.newgan.deal.module.FragmentModule;
import com.example.brusewu.newgan.deal.scope.FragmentScope;
import com.example.brusewu.newgan.ui.modules.main.home.HomeFragment;
import com.example.brusewu.newgan.ui.modules.main.home.tabpage.GanHuoPageFragment;
import com.example.brusewu.newgan.ui.modules.main.more.MoreFragment;
import com.example.brusewu.newgan.ui.modules.main.pictures.PicturesFragment;
import com.example.brusewu.newgan.ui.modules.main.xiandu.XianDuFragment;
import com.example.brusewu.newgan.ui.modules.main.xiandu.tabpage.XianDuPageFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    void inject(HomeFragment homeFragment);
    void inject(GanHuoPageFragment pageFragment);
    void inject(XianDuFragment xianDuFragment);
    void inject(XianDuPageFragment xianDuPageFragment);
    void inject(PicturesFragment picturesFragment);
    void inject(MoreFragment moreFragment);
}
