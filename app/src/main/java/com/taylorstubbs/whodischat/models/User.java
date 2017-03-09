package com.taylorstubbs.whodischat.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Create map of object
     *
     * @return  the map
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("messageThread", messageThread);
        result.put("searchingForThread", searchingForThread);

        return result;
    }
}
