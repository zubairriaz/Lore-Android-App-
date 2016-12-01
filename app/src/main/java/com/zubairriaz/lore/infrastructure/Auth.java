package com.zubairriaz.lore.infrastructure;

import android.content.Context;

/**
 * Created by zubair on 12/1/2016.
 */

public class Auth {
    private Context context;
    private User user;

    public Auth(Context context) {
        this.context = context;
        user = new User();
    }

    public User getUser() {
        return user;
    }
}
