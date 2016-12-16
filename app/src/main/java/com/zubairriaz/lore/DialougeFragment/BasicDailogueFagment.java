package com.zubairriaz.lore.DialougeFragment;

import android.app.DialogFragment;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.zubairriaz.lore.infrastructure.LoreApplication;

/**
 * Created by zubair on 12/9/2016.
 */

public class BasicDailogueFagment extends DialogFragment {
    protected LoreApplication application;
    protected Bus bus;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (LoreApplication) getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bus.unregister(this);
    }
}
