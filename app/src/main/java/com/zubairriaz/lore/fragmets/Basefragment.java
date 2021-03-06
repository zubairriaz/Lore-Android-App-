package com.zubairriaz.lore.fragmets;

import android.app.Fragment;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.zubairriaz.lore.infrastructure.LoreApplication;

/**
 * Created by zubair on 12/2/2016.
 */

public abstract class Basefragment extends Fragment {
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
