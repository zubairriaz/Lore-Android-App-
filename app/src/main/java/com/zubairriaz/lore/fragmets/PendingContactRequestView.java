package com.zubairriaz.lore.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zubairriaz.lore.R;

/**
 * Created by zubair on 12/16/2016.
 */

public class PendingContactRequestView extends Basefragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_contacts,container,false);
        return view;
    }
}
