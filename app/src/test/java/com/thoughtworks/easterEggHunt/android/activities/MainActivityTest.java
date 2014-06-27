package com.thoughtworks.easterEggHunt.android.activities;


import android.content.Intent;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.support.UserInfoResourceHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@Config(emulateSdk=18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldTestRobolectricFramework() {
        MainActivity mainActivity = buildMainActivity();

        TextView helloText = (TextView) mainActivity.findViewById(R.id.hello);

        assertThat(helloText.getText().toString(), is("Hello world!"));
    }

    @Test
    public void shouldGoToRegistrationActivityIfNotRegistered() {
        buildMainActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();

        assertThat(nextStartedActivity.getComponent().getClassName(), is(RegistrationActivity.class.getName()));
    }

    @Test
    public void shouldNotGoToRegistrationActivityIfAlreadyRegistered() {
        UserInfoResourceHelper.writeName("name");
        buildMainActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();

        assertNull(nextStartedActivity);
    }

    private MainActivity buildMainActivity() {
        return Robolectric.buildActivity(MainActivity.class).create().get();
    }
}