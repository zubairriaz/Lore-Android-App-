package com.zubairriaz.lore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zubairriaz.lore.infrastructure.LoreApplication;

/**
 * Created by zubair on 12/1/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected LoreApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (LoreApplication) getApplication();
    }
}
