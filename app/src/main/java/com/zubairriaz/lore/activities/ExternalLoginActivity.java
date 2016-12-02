package com.zubairriaz.lore.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.zubairriaz.lore.R;

public class ExternalLoginActivity extends BaseActivity implements View.OnClickListener {
    public static final String External_Login_Activity = "External_Login_Activity";
    private WebView webView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_login);
        webView = (WebView) findViewById(R.id.external_login_webview);
        button = (Button) findViewById(R.id.external_login_button);
        button.setOnClickListener(this);
        button.setText("Login with " + getIntent().getStringExtra(External_Login_Activity));
    }

    @Override
    public void onClick(View view) {
        if(view == button){
            application.getAuth().getUser().setIsLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
