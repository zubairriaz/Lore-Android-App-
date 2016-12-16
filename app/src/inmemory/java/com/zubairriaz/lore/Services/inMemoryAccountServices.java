package com.zubairriaz.lore.Services;

import android.util.Log;

import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.Service.Account;
import com.zubairriaz.lore.Services.BaseInMemeoryService;
import com.zubairriaz.lore.infrastructure.Auth;
import com.zubairriaz.lore.infrastructure.LoreApplication;
import com.zubairriaz.lore.infrastructure.User;

/**
 * Created by zubair on 12/10/2016.
 */

public class inMemoryAccountServices  extends BaseInMemeoryService {


    public inMemoryAccountServices(LoreApplication application) {
        super(application);


    }
    @Subscribe
    public  void updateProfile (final Account.updateProfileRequest request){
        Log.e("ERROR","REACHED THERE1");
       final Account.updateProfileResponse response = new Account.updateProfileResponse();
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setDisplayName(request.displayName);
                user.setEmail(request.email);
                bus.post(response);
                bus.post(new Account.userDetailsUpdated(user));
            }
        },4000,5000);
        Log.e("ERROR","REACHED THERE1");

    }
    @Subscribe
    public void changePassword (Account.changePasswordRequest request){
        Account.changePasswordResponse  response= new Account.changePasswordResponse();
                if(!request.newPassword.equals(request.confirmNewPassword)){
                    response.setPropertyError("confirm password","password must match");
                }
                if(request.newPassword.length()<3){
                    response.setPropertyError("new password","Password must be greater than 3 characters");
                }
        postDelayed(response);
    }
    @Subscribe
    public void updateAvatar (final Account.changeAvatarRequest request){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
               User user = application.getAuth().getUser();
                user.setAvatarUrl(request.newAvatarUri.toString());
                bus.post(new Account.changeAvatarResponse());
                bus.post(new Account.userDetailsUpdated(user));

            }
        },4000,5000);

    }
    @Subscribe
    public void LoginWithUsername(final Account.loginWithUserRequest request){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.loginWithUserResponse response = new Account.loginWithUserResponse();
                if(request.username.equals("zubair")){
                    response.setPropertyError("username","Invalid username and password");
                }
                loginUser(response);
                bus.post(response);

            }
        },4000,5000);
    }
    @Subscribe
    public void LoginwithExternalToken (Account.loginwithexternalTokenRequest request){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
              Account.loginWithExternalTokenResponse response = new Account.loginWithExternalTokenResponse();

                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }
    @Subscribe
    public void register (Account.registerRequest request){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
              Account.registerResponse response = new Account.registerResponse();
                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }
    @Subscribe
   public void Externalregister (final Account.registerWithExternalTokenRequest request){
       invokeDelayed(new Runnable() {
           @Override
           public void run() {
            Account.registerWithExternalResponse response = new Account.registerWithExternalResponse();
               loginUser(response);
               bus.post(response);
           }
       },1000,2000);

   }
    @Subscribe
    public void LoginLocalToken (Account.loginWithAuthtokenRequest request){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.loginWithAuthtokenResponse response = new Account.loginWithAuthtokenResponse();
                loginUser(response);
                bus.post(response);

            }
        },1000,2000);
    }


    private void loginUser(Account.UserResponse response){
        Auth auth = application.getAuth();
        User user = auth.getUser();
        user.setDisplayName("Zubair");
        user.setEmail("zubairriaz78696@gmail.com");
        user.setUserName("Zubair Riaz");
        user.setId(123);
        user.setAvatarUrl("http://www.gravatar.com/avatar/1?d=identicon");
        user.setIsLoggedIn(true);
        bus.post(new Account.userDetailsUpdated(user));
        auth.setAuthtoken("fakeauthtoken");
        response.displayName = user.getDisplayName();
        response.email=user.getEmail();
        response.userName = user.getUserName();
        response.avatarUrl=user.getAvatarUrl();
        response.Id = user.getId();
        response.authToken= auth.getAuthtoken();
    }
}
