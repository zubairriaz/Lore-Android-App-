package com.zubairriaz.lore.activities;

import android.os.Bundle;

import com.zubairriaz.lore.R;

/**
 * Created by zubair on 12/16/2016.
 */

public class ContactActivity extends BaseAuthenticatedActivity {
    public static final String EXTRA_USER_DETAILS = "EXTRA_USER_DETAILS";
    @Override
    protected void onLoreCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contact);

    }
}
