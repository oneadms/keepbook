package com.keepbook.app.view;

import android.content.Intent;

import com.keepbook.app.R;
import com.keepbook.app.view.base.BaseActivity;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GuideActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void init() {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    }
                },
                new Date(System.currentTimeMillis() + 1500));


    }

    @Override
    protected int initLayout() {
        return R.layout.activity_guide;
    }
}
