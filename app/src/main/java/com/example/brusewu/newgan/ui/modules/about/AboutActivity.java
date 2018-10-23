package com.example.brusewu.newgan.ui.modules.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.brusewu.newgan.R;
import com.example.brusewu.newgan.ui.base.BaseActivity;
import com.example.brusewu.newgan.utils.Constans;

import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(R.string.toolbar_title_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getLayout(){
        return R.layout.activity_about;
    }

    @OnClick(R.id.action_button_1)
    public void onpenProjectPage(){
        Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(Constans.PROJECT_PAGE_URL));
        startActivity(openBrowserIntent);
    }

    @OnClick(R.id.action_button_2)
    public void openIssuesPage(){
        Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(Constans.ISSUE_PAGE_URL));
        startActivity(openBrowserIntent);
    }
}









