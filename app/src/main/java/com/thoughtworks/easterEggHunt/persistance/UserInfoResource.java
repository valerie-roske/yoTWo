package com.thoughtworks.easterEggHunt.persistance;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoResource {
    public static final String USER_PREFS = "USER_PREFS";
    public static final String USER_NAME = "name";

    private final SharedPreferences sharedPreferences;

    public UserInfoResource(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
    }

    public String getName() {
        return sharedPreferences.getString(USER_NAME, "");
    }
}
