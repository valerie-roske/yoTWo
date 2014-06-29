package com.thoughtworks.easterEggHunt.persistance;

import com.thoughtworks.easterEggHunt.support.UserInfoResourceHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UserInfoResourceTest {
    private static final String NAME = "name";

    @Before
    public void setUp() throws Exception {
        UserInfoResourceHelper.writeName(NAME);
    }

    @Test
    public void shouldGetResourcesFromSharedPrefs() {
        UserInfoResource userInfoResource = new UserInfoResource(Robolectric.application);

        String name = userInfoResource.getName();

        assertThat(name, is(NAME));
    }
}