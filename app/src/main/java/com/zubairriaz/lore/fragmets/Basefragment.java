package com.zubairriaz.lore.fragmets;

import android.app.Fragment;
import android.os.Bundle;

import com.zubairriaz.lore.infrastructure.LoreApplication;

/**
 * Created by zubair on 12/2/2016.
 */

public abstract class Basefragment extends Fragment {
    protected LoreApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (LoreApplication) getActivity().getApplication();
    }
}
