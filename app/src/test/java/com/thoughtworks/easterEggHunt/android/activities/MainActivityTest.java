package com.thoughtworks.easterEggHunt.android.activities;


import android.content.Intent;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.support.UserInfoResourceHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    @Test
    @Ignore("Code is commented out right now")
    public void shouldGoToRegistrationActivityIfNotRegistered() {
        createMainActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();

        assertThat(nextStartedActivity).isEqualTo(intentFor(RegistrationActivity.class));
    }

    @Test
    public void shouldNotGoToRegistrationActivityIfAlreadyRegistered() {
        UserInfoResourceHelper.writeName("name");
        createMainActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();

        assertNull(nextStartedActivity);
    }

    @Test
    public void shouldStartListUsersActivityWhenButtonClicked() {
        UserInfoResourceHelper.writeName("name");
        MainActivity activity = createMainActivity().resume().get();

        activity.findViewById(R.id.send_message_button).performClick();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(intentFor(ListUsersActivity.class));
    }

    private Intent intentFor(Class nextStartedActivity) {
        return new Intent(Robolectric.application, nextStartedActivity);
    }

    private ActivityController<MainActivity> createMainActivity() {
        return Robolectric.buildActivity(MainActivity.class).create();
    }
}