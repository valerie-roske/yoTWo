package com.thoughtworks.easterEggHunt.persistance;

import android.content.Context;
import android.content.SharedPreferences;
import com.thoughtworks.easterEggHunt.domain.User;

public class UserInfoResource {
    public static final String USER_PREFS = "USER_PREFS";
    public static final String USER_NAME = "name";
    public static final String USER_ID = "id";

    private final SharedPreferences sharedPreferences;

    public UserInfoResource(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
    }

    public User getUser() {
        Integer id = sharedPreferences.getInt(USER_ID, User.MISSING_ID);
        String name = sharedPreferences.getString(USER_NAME, "");

        return new User(id, name);
    }

    public void save(User user) {
        sharedPreferences.edit().putInt(USER_ID, user.getId())
                                .putString(USER_NAME, user.getName())
                                .commit();
    }
}
