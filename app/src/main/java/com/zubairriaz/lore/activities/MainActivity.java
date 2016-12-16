package com.zubairriaz.lore.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.zubairriaz.lore.R;



/**
 * Created by zubair on 12/1/2016.
 */

public class MainActivity extends BaseAuthenticatedActivity {
   protected Toolbar toolbar;
    public static int selected_item;
    @Override
    protected void onLoreCreate(Bundle savedInstanceState) {
        setContentView(R.layout.actvity_main);
        getSupportActionBar().setTitle("INBOX");
         createNavDrawer();


    }
}
