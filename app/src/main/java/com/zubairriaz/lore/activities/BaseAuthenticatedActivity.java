package com.zubairriaz.lore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by zubair on 12/1/2016.
 */

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!application.getAuth().getUser().getIsLoggedIn()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        onLoreCreate(savedInstanceState);
    }
    protected abstract void onLoreCreate (Bundle savedInstanceState);
}
