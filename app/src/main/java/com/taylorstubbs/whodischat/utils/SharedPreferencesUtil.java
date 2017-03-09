package com.taylorstubbs.whodischat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Utility for working with SharedPreferences
 */

public enum SharedPreferencesUtil {;
    private static final String TAG = "SharedPreferencesUtil";
    private static final String USER_PASSWORD_KEY = "userPasswordKey";
    private static final String USER_EMAIL_KEY = "userEmailKey";

    /**
     * Set the user password.
     *
     * @param context   the activity context
     * @param userName  the password to set
     */
    public static void setUserPassword(Context context, String userName) {
        getSharedPreferences(context)
                .edit()
                .putString(USER_PASSWORD_KEY, userName)
                .apply();
    }

    /**
     * Set the user ID.
     *
     * @param context   the activity context
     * @param userId    the ID to set
     */
    public static void setUserId(Context context, String userId) {
        getSharedPreferences(context)
                .edit()
                .putString(USER_EMAIL_KEY, userId)
                .apply();
    }

    /**
     * Get the user password.
     *
     * @param context   the activity context
     * @return          the user password
     */
    public static String getUserPassword(Context context) {
        return getSharedPreferences(context).getString(USER_PASSWORD_KEY, null);
    }

    /**
     * Get the user email.
     *
     * @param context   the activity context
     * @return          the user email
     */
    public static String getUserEmail(Context context) {
        return getSharedPreferences(context).getString(USER_EMAIL_KEY, null);
    }

    /**
     * Get SharedPreferences.
     *
     * @param context   the context
     * @return          SharedPreferences
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
