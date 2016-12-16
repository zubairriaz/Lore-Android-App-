package com.zubairriaz.lore.activities;

import android.os.Bundle;

import com.zubairriaz.lore.R;

/**
 * Created by zubair on 12/8/2016.
 */

public class SentMessagesActivity extends BaseAuthenticatedActivity{
    @Override
    protected void onLoreCreate(Bundle savedInstanceState) {
        setContentView(R.layout.actvity_messages);
        createNavDrawer();

    }
}
