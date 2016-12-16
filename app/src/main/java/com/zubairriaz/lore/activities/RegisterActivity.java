package com.zubairriaz.lore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.R;
import com.zubairriaz.lore.Service.Account;

/**
 * Created by zubair on 12/2/2016.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private EditText emailText;
    private Button registerButton;
    private View progressView;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameText = (EditText) findViewById(R.id.activity_register_username);
        passwordText = (EditText) findViewById(R.id.activity_register_password);
        emailText = (EditText) findViewById(R.id.activity_register_email);
        registerButton= (Button) findViewById(R.id.activity_register_registerbutton);
        progressView = (View) findViewById(R.id.activity_regiter_progressbar);
        registerButton.setOnClickListener(this);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if(view == registerButton){
            progressView.setVisibility(View.VISIBLE);
            registerButton.setText("");
            emailText.setEnabled(false);
            usernameText.setEnabled(false);
            passwordText.setEnabled(false);
            registerButton.setEnabled(false);
       bus.post(new Account.registerRequest(usernameText.getText().toString(),
               passwordText.getText().toString(),emailText.getText().toString()));
    }
    }
    @Subscribe
    public void onRegisterResponse(Account.registerResponse response){
        onUserResponse(response);
    }
    @Subscribe
    public void onExternalRegisterResponse (Account.registerWithExternalResponse response){
        onUserResponse(response);
    }
    private void onUserResponse(Account.UserResponse response){
        if(response.didSucceed()){
            setResult(RESULT_OK);
            finish();
            return;
        }
        response.showErrorToast(this);
        usernameText.setError(response.getPropertyError("username"));
        passwordText.setError(response.getPropertyError("password"));
        emailText.setError(response.getPropertyError("email"));

        registerButton.setEnabled(true);
        usernameText.setEnabled(true);
        passwordText.setEnabled(true);
        emailText.setEnabled(true);
        progressView.setVisibility(View.GONE);
        registerButton.setText("Register");
    }
}
