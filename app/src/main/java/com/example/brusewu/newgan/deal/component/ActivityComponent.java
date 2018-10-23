package com.example.brusewu.newgan.deal.component;



import com.example.brusewu.newgan.deal.module.ActivityModule;
import com.example.brusewu.newgan.deal.scope.ActivityScope;
import com.example.brusewu.newgan.ui.modules.imageviewer.ImageViewerActivity;
import com.example.brusewu.newgan.ui.modules.main.MainActivity;
import com.example.brusewu.newgan.ui.modules.main.xiandu.list.XianDuListActivity;
import com.example.brusewu.newgan.ui.modules.search.SearchActivity;
import com.example.brusewu.newgan.ui.modules.settings.SettingsActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(XianDuListActivity xianDuListActivity);
    void inject(ImageViewerActivity imageViewerActivity);
    void inject(SearchActivity searchActivity);
    void inject(SettingsActivity settingsActivity);

}
