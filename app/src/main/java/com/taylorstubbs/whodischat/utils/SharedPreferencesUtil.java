package com.taylorstubbs.whodischat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Utility for working with SharedPreferences
 */

public enum SharedPreferencesUtil {;
    private static final String TAG = "SharedPreferencesUtil";
    private static final String KEY_IS_FRESH_INSTALL = "isFreshInstall";

    /**
     * Set isFreshInstall.
     *
     * @param context           the activity context
     * @param isFreshInstall    is this a fresh install
     */
    public static void setIsFreshInstall(Context context, boolean isFreshInstall) {
        getSharedPreferences(context)
                .edit()
                .putBoolean(KEY_IS_FRESH_INSTALL, isFreshInstall)
                .apply();
    }

    /**
     * Get isFreshInstall.
     *
     * @param context   the activity context
     * @return          the user email
     */
    public static Boolean getIsFreshInstall(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_IS_FRESH_INSTALL, true);
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
