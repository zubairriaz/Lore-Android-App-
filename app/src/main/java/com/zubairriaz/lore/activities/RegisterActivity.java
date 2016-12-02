package com.zubairriaz.lore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zubairriaz.lore.R;

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
        application.getAuth().getUser().setIsLoggedIn(true);
        setResult(RESULT_OK);
        finish();
    }
}
