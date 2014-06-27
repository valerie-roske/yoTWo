package com.thoughtworks.easterEggHunt.persistance;

import android.content.Context;
import android.content.SharedPreferences;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Config(emulateSdk=18)
@RunWith(RobolectricTestRunner.class)
public class UserInfoResourceTest {

    public static final String NAME = "name";
    private Context context;

    @Before
    public void setUp() throws Exception {
        context = Robolectric.application;
        SharedPreferences sharedPreferences = Robolectric.getShadowApplication().getSharedPreferences(UserInfoResource.USER_PREFS, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(UserInfoResource.USER_NAME, NAME).commit();
    }

    @Test
    public void shouldGetResourcesFromSharedPrefs() {
        UserInfoResource userInfoResource = new UserInfoResource(context);

        String name = userInfoResource.getName();

        assertThat(name, is(NAME));
    }


}