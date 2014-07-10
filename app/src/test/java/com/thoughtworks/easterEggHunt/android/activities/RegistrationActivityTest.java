package com.thoughtworks.easterEggHunt.android.activities;

import android.content.Intent;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.persistance.UserInfoResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RegistrationActivityTest {
    @Test
    public void shouldSaveTheUserToSharedPrefsWhenRegistrationIsSuccessful() throws Exception {
        RegistrationActivity activity = Robolectric.buildActivity(RegistrationActivity.class).create().get();

        activity.success(new User(1, "name"));

        User user = new UserInfoResource(Robolectric.application).getUser();
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("name"));
    }

    @Test
    public void shouldStartTheMainActivityWhenRegistrationIsSuccessful() throws Exception {
        RegistrationActivity activity = Robolectric.buildActivity(RegistrationActivity.class).create().get();

        activity.success(new User(1, "name"));

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(intentFor(MainActivity.class));
    }

    private Intent intentFor(Class nextStartedActivity) {
        return new Intent(Robolectric.application, nextStartedActivity);
    }
}