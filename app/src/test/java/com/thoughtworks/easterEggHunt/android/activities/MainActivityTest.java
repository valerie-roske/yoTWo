package com.thoughtworks.easterEggHunt.android.activities;

import android.content.Intent;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.support.UserInfoResourceHelper;
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
    public void shouldGoToRegistrationActivityIfNotRegistered() {
        resumeMainActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(intentFor(RegistrationActivity.class));
    }

    @Test
    public void shouldNotGoToRegistrationActivityIfAlreadyRegistered() {
        userIsRegistered();

        resumeMainActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertNull(nextStartedActivity);
    }

    @Test
    public void shouldStartListUsersActivityWhenButtonClicked() {
        userIsRegistered();

        MainActivity activity = resumeMainActivity().get();
        activity.findViewById(R.id.send_message_button).performClick();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(intentFor(ListUsersActivity.class));
    }

    private Intent intentFor(Class nextStartedActivity) {
        return new Intent(Robolectric.application, nextStartedActivity);
    }

    private ActivityController<MainActivity> resumeMainActivity() {
        return Robolectric.buildActivity(MainActivity.class).create().resume();
    }

    private void userIsRegistered() {
        UserInfoResourceHelper.writeId(1);
        UserInfoResourceHelper.writeName("Test name");
    }
}