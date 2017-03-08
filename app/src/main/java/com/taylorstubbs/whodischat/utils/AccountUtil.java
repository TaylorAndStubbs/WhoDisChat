package com.taylorstubbs.whodischat.utils;

import java.util.UUID;

/**
 * Contains some useful functions for working with accounts
 */

public enum AccountUtil {;
    private static final String TAG = "AccountUtil";

    /**
     * Append a domain onto the end of the ID. This allows the id to be used as an email.
     *
     * @param id    the user's id
     * @return      the fake email
     */
    public static String appendDomainToId(String id) {
        //personal domain
        return id + "@taylorstubbs.com";
    }

    /**
     * Create a random password.
     *
     * @return  the password
     */
    public static String createRandomPassword() {
        return UUID.randomUUID().toString();
    }
}
