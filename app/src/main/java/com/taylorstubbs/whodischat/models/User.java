package com.taylorstubbs.whodischat.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * User.
 */

@IgnoreExtraProperties
public class User {
    private static final String TAG = "User";

    public String userId;
    public String messageThread;
    public Boolean searchingForThread;

    public User() {
        //Default Constructor required
    }

    public User(String userId) {
        this.userId = userId;
    }
}
