package com.zubairriaz.lore.Service;

import android.net.Uri;
import android.util.Log;

import com.zubairriaz.lore.infrastructure.ServiceResponse;
import com.zubairriaz.lore.infrastructure.User;

/**
 * Created by zubair on 12/9/2016.
 */

public final class Account {
    private Account(){

    }
public static class UserResponse extends ServiceResponse{
    public int Id;
    public String userName;
    public String displayName;
    public String avatarUrl;
    public  String email;
    public String authToken;
    public boolean hasPassword;
}
    public static class loginWithUserRequest {
        public String username;
        public String password;

        public loginWithUserRequest(String password, String username) {
            this.password = password;
            this.username = username;
        }
    }
    public static class loginWithUserResponse extends UserResponse{

    }
    public static class loginWithAuthtokenRequest{
        public String authToken;

        public loginWithAuthtokenRequest(String authToken) {
            this.authToken = authToken;
        }
    }
    public static class loginWithAuthtokenResponse extends UserResponse{

    }
    public static class loginwithexternalTokenRequest {
        public String provider;
        public String token;
        public String clientId;

        public loginwithexternalTokenRequest(String token, String provider, String clientId) {
            this.token = token;
            this.provider = provider;
            this.clientId ="android";
        }
    }
    public static class loginWithExternalTokenResponse extends UserResponse{

    }
    public static class registerRequest{
        public String username;
        public String password;
        public String clientId;
        public String Email;

        public registerRequest(String username, String password,  String email) {
            this.username = username;
            this.password = password;
            clientId = "android";
            Email = email;
        }
    }
    public static class registerResponse extends UserResponse{

    }
    public static class registerWithExternalTokenRequest{
         public String Username;
         public String Email;
        public String Provider;
        public String Token;
        public String ClinetId;

        public registerWithExternalTokenRequest(String username, String token, String provider, String email) {
            Username = username;
            Token = token;
            Provider = provider;
            Email = email;
            ClinetId = "android";
        }
    }
    public static class registerWithExternalResponse extends UserResponse{

    }
public static class changeAvatarRequest{
    public Uri newAvatarUri;

    public changeAvatarRequest(Uri newAvatarUri){
           this.newAvatarUri = newAvatarUri;
    }

}
    public static class changeAvatarResponse extends ServiceResponse{

    }
    public static class updateProfileRequest{
        public String displayName;
        public   String email;


        public updateProfileRequest(String displayName, String email) {
            this.displayName = displayName;
            this.email = email;
            Log.e("ERROR","REACHED THEREuppp");
        }

    }
   public static class updateProfileResponse extends ServiceResponse{
   }
    public static class changePasswordRequest extends ServiceResponse {
        public String currentPassword;
        public String newPassword;
        public String confirmNewPassword;

        public changePasswordRequest(String currentPassword, String newPassword, String confirmNewPassword) {
            this.currentPassword = currentPassword;
            this.newPassword = newPassword;
            this.confirmNewPassword = confirmNewPassword;

        }
    }
    public static class changePasswordResponse extends ServiceResponse{

    }
    public static class userDetailsUpdated{
        public User user;

        public userDetailsUpdated(User user) {
            this.user = user;
        }
    }

}
