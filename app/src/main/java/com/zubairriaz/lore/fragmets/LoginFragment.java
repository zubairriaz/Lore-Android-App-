package com.zubairriaz.lore.fragmets;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.R;
import com.zubairriaz.lore.Service.Account;
import com.zubairriaz.lore.activities.BaseActivity;

/**
 * Created by zubair on 12/2/2016.
 */

public class LoginFragment extends Basefragment implements View.OnClickListener {
     private Button loginButton;
    private Callbacks callbacks;
    private ProgressBar progressbar;
    private EditText usernameText;
    private EditText passwordText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        loginButton = (Button) view.findViewById(R.id.fragment_login_loginbutton);
        progressbar = (ProgressBar) view.findViewById(R.id.fragment_login_progressBar);
        usernameText=(EditText) view.findViewById(R.id.fragment_login_username) ;
        passwordText=(EditText) view.findViewById(R.id.fragment_login_password);
        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks =null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context = getActivity();
        callbacks = (Callbacks) context;
    }

    @Override
    public void onClick(View view) {

        if(view == loginButton){
            progressbar.setVisibility(View.VISIBLE);
            loginButton.setText("");
            loginButton.setEnabled(false);
            usernameText.setEnabled(false);
            passwordText.setEnabled(false);
            bus.post(new Account.loginWithUserRequest(passwordText.getText().toString(),usernameText.getText().toString()));

        }
    }
    @Subscribe
    public void onLoginwithUsernname (Account.loginWithUserResponse response){
        response.showErrorToast(getActivity());
        if(response.didSucceed()){
            callbacks.onLoggedIn();
            return;
        }
        usernameText.setEnabled(true);
        usernameText.setError(response.getPropertyError("username"));

        passwordText.setError(response.getPropertyError("password"));
        passwordText.setEnabled(true);

        progressbar.setVisibility(View.GONE);
        loginButton.setText("LOG IN");
        loginButton.setEnabled(true);

    }
   public interface Callbacks {
        void onLoggedIn();
    }
}
