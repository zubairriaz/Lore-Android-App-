package com.zubairriaz.lore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zubairriaz.lore.R;
import com.zubairriaz.lore.fragmets.LoginFragment;

/**
 * Created by zubair on 12/1/2016.
 */

public class LoginActivity extends BaseActivity implements LoginFragment.Callbacks {
    private static final int Request_Narrow_Login = 1;
    private static final int Request_Register_Code= 2;
    private Button button;
    private Button register;
    private Button buttonGoogle;
    private Button buttonFacebook;
    private static final int Request_External_Login =3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.activity_login_signin);
        register = (Button) findViewById(R.id.activity_login_register);
        buttonFacebook = (Button) findViewById(R.id.activity_login_facebook) ;
        buttonGoogle = (Button) findViewById(R.id.activity_login_google);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doExternalLogin("Facebook");
                
            }
        });
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doExternalLogin("Google");
            }
        });
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(LoginActivity.this,RegisterActivity.class),Request_Register_Code);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,LoginNarrowActivity.class);
                startActivityForResult(intent, Request_Narrow_Login);
            }
        });
    }

    protected void doExternalLogin(String externalService) {
        Intent intent = new Intent(LoginActivity.this,ExternalLoginActivity.class);
        intent.putExtra(ExternalLoginActivity.External_Login_Activity,externalService);
        startActivityForResult(intent,Request_External_Login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Request_Narrow_Login || requestCode == Request_Register_Code || requestCode==Request_External_Login){
            finishLogin();
        }else
            return;
    }
    private void finishLogin(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void onLoggedIn() {
        finishLogin();
    }
}
