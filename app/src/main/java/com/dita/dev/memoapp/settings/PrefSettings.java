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
    public static final String PREF_LOGGED_IN = "pref_signin_status";
    public static final String PREF_USER_NAME = "pref_user_name";
    public static final String PREF_USER_EMAIL = "pref_user_email";
    public static final String PREF_USER_DESCRIPTION = "pref_user_description";
    public static final String PREF_USER_PROFILE_IMAGE = "pref_user_profile_image";

    public static final String PREF_ = "pref_";


    public static boolean isLoggedIn(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_LOGGED_IN, false);
    }

    public static void markLoggedIn(final Context context, boolean newValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_LOGGED_IN, newValue).apply();
    }

    public static String getPrefUserName(final Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PREF_USER_NAME, "Enter Username");
    }

    public static void setPrefUserName(final Context context, String userName) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_USER_NAME, userName).apply();
    }

    public static String getPrefUserEmail(final Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PREF_USER_EMAIL, "Enter Email");
    }

    public static void setPrefUserEmail(final Context context, String userEmail) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_USER_EMAIL, userEmail).apply();
    }

    public static String getPrefUserDescription(final Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PREF_USER_DESCRIPTION, "Enter Description");
    }

    public static void setPrefUserDescription(final Context context, String userDescription) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_USER_DESCRIPTION, userDescription).apply();
    }

    public static String getPrefUserProfileImage(final Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PREF_USER_PROFILE_IMAGE, null);
    }

    public static void setPrefUserProfileImage(final Context context, String uri) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_USER_PROFILE_IMAGE, uri).apply();
    }

    public static void setPref(final Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).apply();
    }
}
