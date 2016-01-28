package com.dita.dev.memoapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by marvel on 11/18/15.
 */
public class PrefSettings {
    /**
     * Boolean indicating whether ToS has been accepted.
     */
    public static final String PREF_LOGGED_IN = "pref_tos_accepted";


    public static boolean isLoggedIn(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_LOGGED_IN, false);
    }

    public static void markLoggedIn(final Context context, boolean newValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_LOGGED_IN, newValue).apply();
    }
}
