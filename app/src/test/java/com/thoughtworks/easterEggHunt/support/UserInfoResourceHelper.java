package com.thoughtworks.easterEggHunt.support;

import android.content.Context;
import android.content.SharedPreferences;
import com.thoughtworks.easterEggHunt.persistance.UserInfoResource;
import org.robolectric.Robolectric;

public class UserInfoResourceHelper {
    public static void writeName(String name) {
        SharedPreferences sharedPreferences = Robolectric.getShadowApplication().getSharedPreferences(UserInfoResource.USER_PREFS, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(UserInfoResource.USER_NAME, name).commit();
    }
}
