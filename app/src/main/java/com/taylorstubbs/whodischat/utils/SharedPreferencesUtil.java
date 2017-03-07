package com.taylorstubbs.whodischat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.taylorstubbs.whodischat.R;

/**
 * Utility for working with SharedPreferences
 */

public enum SharedPreferencesUtil {;
    private static final String TAG = "SharedPreferencesUtil";
    private static final String USER_NAME_KEY = "userNameKey";
    private static final String USER_ID_KEY = "userIdKey";

    /**
     * Set the user name.
     *
     * @param context   the activity context
     * @param userName  the username to set
     */
    public static void setUserName(Context context, String userName) {
        getSharedPreferences(context)
                .edit()
                .putString(USER_NAME_KEY, userName)
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
                .putString(USER_ID_KEY, userId)
                .apply();
    }

    /**
     * Get the username.
     *
     * @param context   the activity context
     * @return          the user name
     */
    public static String getUserName(Context context) {
        return getSharedPreferences(context).getString(USER_NAME_KEY, null);
    }

    /**
     * Get the user ID.
     *
     * @param context   the activity context
     * @return          the user ID
     */
    public static String getUserId(Context context) {
        return getSharedPreferences(context).getString(USER_ID_KEY, null);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
