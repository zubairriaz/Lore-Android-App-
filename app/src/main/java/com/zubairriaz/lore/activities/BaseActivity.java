package com.zubairriaz.lore.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.R;
import com.zubairriaz.lore.Service.Account;
import com.zubairriaz.lore.infrastructure.LoreApplication;



/**
 * Created by zubair on 12/1/2016.
 */

public abstract class  BaseActivity extends AppCompatActivity {
    protected LoreApplication application;
    protected Toolbar toolbar;
    protected Bus bus;
    String TITLES[] = {"INBOX","CONTACTS","SENT MESSAGES","PROFILE","LOGOUT"};
    int ICONS[] = {R.drawable.ic_action_unread,R.drawable.ic_action_group,R.drawable.ic_action_unread,R.drawable.ic_action_person,R.drawable.ic_action_backspace};

    public static String NAME ;
    public static  String EMAIL;
    int PROFILE = R.drawable.ic_action_person;
    public RecyclerView mrecyclerView;                           // Declaring RecyclerView
    public RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    public RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    public DrawerLayout Drawer;                                  // Declaring DrawerLayout

    public ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        application = (LoreApplication) getApplication();
        bus = application.getBus();
        bus.register(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        toolbar = (Toolbar) findViewById(R.id.lore_toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }}
                     // Finally we set the drawer toggle sync State}


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    public boolean isOpen() {
        return Drawer.isDrawerOpen(GravityCompat.START);
    }
    public void setOpen(boolean isOpen) {
        if (isOpen) {
            Drawer.openDrawer(GravityCompat.START);
        } else {
            Drawer.closeDrawer(GravityCompat.START);
        }
    }
    public void createNavDrawer(){
        mrecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mrecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        mrecyclerView.setAdapter(mAdapter);// Setting the adapter to RecyclerView
        final GestureDetector gestureDetector = new GestureDetector(getBaseContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
        mrecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child=  rv.findChildViewUnder(e.getX(),e.getY());
                if(child !=null && gestureDetector.onTouchEvent(e)){
                    Drawer.closeDrawer(GravityCompat.START);
                     if(mrecyclerView.getChildAdapterPosition(child) == 1){
                         fadeOut(new fadeOutListener() {
                             @Override
                             public void OnFadeOutEnd() {
                                 startActivity(new Intent(getBaseContext(),MainActivity.class));
                                 finish();
                             }
                         });

                     }
                    else if(mrecyclerView.getChildAdapterPosition(child)==2){
                         fadeOut(new fadeOutListener() {
                             @Override
                             public void OnFadeOutEnd() {
                                 startActivity(new Intent(getBaseContext(),ContactsActivity.class));
                                 finish();
                             }
                         });
                     }
                    else if(mrecyclerView.getChildAdapterPosition(child)==3){
                         fadeOut(new fadeOutListener() {
                             @Override
                             public void OnFadeOutEnd() {
                                 startActivity(new Intent(getBaseContext(),SentMessagesActivity.class));
                                 finish();
                             }
                         });
                     }
                    else if(mrecyclerView.getChildAdapterPosition(child)==4){
                         fadeOut(new fadeOutListener() {
                             @Override
                             public void OnFadeOutEnd() {
                                 startActivity(new Intent(getBaseContext(),ProfileActivity.class));
                                 finish();
                             }
                         });

                         }
                     else if (mrecyclerView.getChildAdapterPosition(child) == 5){
                         application.getAuth().logOut();
                     }
                }
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mrecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        // Drawer Toggle Object Made
        toolbar = getToolbar();
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOpen(!isOpen());
            }
        });
        overridePendingTransition(0,0);
        View rootView = findViewById(android.R.id.content);
        rootView.setAlpha(0);
        rootView.animate().alpha(1).setDuration(450).start();
    }
    public Toolbar getToolbar() {
        return toolbar;
    }
    public void fadeOut (final fadeOutListener listener){
        View rootview = findViewById(android.R.id.content);
        rootview.animate().alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                   listener.OnFadeOutEnd();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).setDuration(300).start();
    }

         public interface fadeOutListener{
             void OnFadeOutEnd();
         }

}
