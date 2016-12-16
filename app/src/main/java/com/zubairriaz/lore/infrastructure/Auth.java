package com.zubairriaz.lore.infrastructure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.zubairriaz.lore.activities.LoginActivity;

/**
 * Created by zubair on 12/1/2016.
 */

public class Auth {
    private Context context;
    private User user;
    private String authtoken;
    private final String AUTH_PREFRENCES = "AUTH_PREFRENCES";
    private final String AUTH_PREFRENCES_TOKEN = "AUTH_PREFRENCES_TOKEN";
    private final SharedPreferences prefrences;

    public Auth(Context context) {
        this.context = context;
        user = new User();
        prefrences = context.getSharedPreferences(AUTH_PREFRENCES,Context.MODE_PRIVATE);
       authtoken =  prefrences.getString(AUTH_PREFRENCES_TOKEN,null);

    }

    public User getUser() {
        return user;
    }
    public String getAuthtoken(){
        return authtoken;
    }
    public boolean hasAuthToken(){
        return (authtoken != null && !authtoken.isEmpty());
    }
    public void setAuthtoken(String authtoken){
        this.authtoken = authtoken;

        SharedPreferences.Editor editor = prefrences.edit();
        editor.putString(AUTH_PREFRENCES_TOKEN,authtoken);
        editor.commit();
    }
    public  void logOut(){
        setAuthtoken(null);
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
