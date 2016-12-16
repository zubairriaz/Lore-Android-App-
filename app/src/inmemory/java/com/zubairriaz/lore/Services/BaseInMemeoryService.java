package com.zubairriaz.lore.Services;

import android.os.Handler;

import com.squareup.otto.Bus;
import com.zubairriaz.lore.infrastructure.LoreApplication;

import java.util.Random;

/**
 * Created by zubair on 12/10/2016.
 */

public abstract class BaseInMemeoryService {
    protected final Bus bus;
    protected final Handler handler;
    protected final LoreApplication application;
    protected final Random random;

    public BaseInMemeoryService(LoreApplication application) {
        this.application = application;
        bus = application.getBus();
        handler = new Handler();
        random = new Random();
        bus.register(this);
    }
    protected void invokeDelayed(Runnable runnable, long millisecondsMin, long millisecondsMax){
        if(millisecondsMin > millisecondsMax){
            throw  new IllegalArgumentException("cant be possible");
        }
        long delay = (long)(random.nextDouble()* (millisecondsMax - millisecondsMin))+millisecondsMin;
        handler.postDelayed( runnable,delay);
    }
    protected void postDelayed (final Object event, long millisecondsMin, long millsecondsMax){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        },millisecondsMin,millsecondsMax);
    }
    protected void postDelayed(Object event, long millisecond){
        postDelayed(event,millisecond,millisecond);
    }
    protected void postDelayed(Object event){
        postDelayed(event,600,1200);
    }
}
