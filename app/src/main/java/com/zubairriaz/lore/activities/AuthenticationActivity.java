package com.zubairriaz.lore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.R;
import com.zubairriaz.lore.Service.Account;

/**
 * Created by zubair on 12/13/2016.
 */
public class AuthenticationActivity  extends BaseActivity{
    public static final String RETURN_TO_ACTIVITY = "RETURN_TO_ACTIVITY";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        if(!application.getAuth().hasAuthToken()){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }
        bus.post(new Account.loginWithAuthtokenRequest(application.getAuth().getAuthtoken()));


    }
    @Subscribe
    public void onLoginwithAuthToken (Account.loginWithAuthtokenResponse response){
        if(!response.didSucceed()){
            Toast.makeText(this, "Login Again", Toast.LENGTH_SHORT).show();
            application.getAuth().setAuthtoken(null);
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }
        Intent intent;
        String returnto = getIntent().getStringExtra(RETURN_TO_ACTIVITY);
        if(returnto != null){
            try {
                intent = new Intent(this,Class.forName(returnto));
            }catch (Exception ignored){
              intent = new Intent(this,MainActivity.class);
            }

        }else {
            intent = new Intent(this,MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
