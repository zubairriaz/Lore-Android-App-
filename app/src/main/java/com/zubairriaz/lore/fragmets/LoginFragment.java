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

import com.zubairriaz.lore.R;
import com.zubairriaz.lore.activities.BaseActivity;

/**
 * Created by zubair on 12/2/2016.
 */

public class LoginFragment extends Basefragment implements View.OnClickListener {
     private Button loginButton;
    private Callbacks callbacks;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        loginButton = (Button) view.findViewById(R.id.fragment_login_loginbutton);
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
            application.getAuth().getUser().setIsLoggedIn(true);
           callbacks.onLoggedIn();
        }
    }
   public interface Callbacks {
        void onLoggedIn();
    }
}
