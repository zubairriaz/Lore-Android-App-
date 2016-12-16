package com.zubairriaz.lore.activities;

import android.os.Bundle;

import com.zubairriaz.lore.R;

/**
 * Created by zubair on 12/16/2016.
 */

public class AddContactActivity extends BaseAuthenticatedActivity {
    public static final String RESULT_CONTACT = "RESULT_CONTACT";
    @Override
    protected void onLoreCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcontact);

    }
}
