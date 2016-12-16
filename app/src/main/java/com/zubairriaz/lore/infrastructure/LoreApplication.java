package com.zubairriaz.lore.infrastructure;

import android.app.Application;

import com.squareup.otto.Bus;
import com.zubairriaz.lore.Services.Module;

/**
 * Created by zubair on 12/1/2016.
 */

public class LoreApplication extends Application {


    private Auth auth;
    private Bus bus;

    public LoreApplication(){
        bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
        Module.register(this);


    }

    public Auth getAuth() {
        return auth;
    }

    public Bus getBus() {
        return bus;
    }
}
