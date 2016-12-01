package com.zubairriaz.lore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zubairriaz.lore.R;

/**
 * Created by zubair on 12/1/2016.
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void doLoginIn(View view) {
        application.getAuth().getUser().setIsLoggedIn(true);
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}
