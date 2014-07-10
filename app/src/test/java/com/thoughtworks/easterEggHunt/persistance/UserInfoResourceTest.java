package com.thoughtworks.easterEggHunt.persistance;

import com.thoughtworks.easterEggHunt.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UserInfoResourceTest {
    private UserInfoResource userInfoResource;

    @Before
    public void setUp() throws Exception {
        userInfoResource = new UserInfoResource(Robolectric.application);
    }

    @Test
    public void shouldGetResourcesFromSharedPrefs() {
        userInfoResource.save(new User(1, "name"));

        User user = userInfoResource.getUser();

        assertThat(user.exists(), is(true));
        assertThat(user.getName(), is("name"));
        assertThat(user.getId(), is(1));
    }

    @Test
    public void shouldGetNoUserWhenTheUserDoesNotExist() {
        User user = userInfoResource.getUser();

        assertThat(user.exists(), is(false));
    }
}