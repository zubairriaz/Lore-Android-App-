package com.zubairriaz.lore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zubair on 12/1/2016.
 */

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(!application.getAuth().getUser().getIsLoggedIn()){
            if(application.getAuth().hasAuthToken()){
                Intent intent = new Intent(this, AuthenticationActivity.class);
                intent.putExtra(AuthenticationActivity.RETURN_TO_ACTIVITY,getClass().getName());
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
            }

            finish();
            return;

        }

        onLoreCreate(savedInstanceState);
    }
    protected abstract void onLoreCreate (Bundle savedInstanceState);
}
