package com.zubairriaz.lore.infrastructure;

import android.app.Application;

/**
 * Created by zubair on 12/1/2016.
 */

public class LoreApplication extends Application {


    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
    }

    public Auth getAuth() {
        return auth;
    }
}
