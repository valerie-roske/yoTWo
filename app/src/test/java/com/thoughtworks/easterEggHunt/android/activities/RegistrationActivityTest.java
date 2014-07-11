package com.thoughtworks.easterEggHunt.android.activities;

import android.content.Intent;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.api.ApiError;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.persistance.UserInfoResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import retrofit.RetrofitError;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class RegistrationActivityTest {

    @Test
    public void shouldStartTheMainActivityIfTheUserIsAlreadyRegistered() throws Exception {
        userIsRegistered();

        RegistrationActivity activity = resumeRegistrationActivity();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(intentFor(MainActivity.class));
        assertThat(activity).isFinishing();
    }

    @Test
    public void shouldSaveTheUserToSharedPrefsWhenRegistrationIsSuccessful() throws Exception {
        RegistrationActivity activity = resumeRegistrationActivity();

        activity.success(new User(1, "name"));

        User user = new UserInfoResource(Robolectric.application).getUser();
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("name"));
    }

    @Test
    public void shouldStartTheMainActivityWhenRegistrationIsSuccessful() throws Exception {
        RegistrationActivity activity = resumeRegistrationActivity();

        activity.success(new User(1, "name"));

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(intentFor(MainActivity.class));
    }

    @Test
    public void shouldShowAnErrorWhenRegistrationIsUnsuccessful() throws Exception {
        RegistrationActivity activity = resumeRegistrationActivity();

        activity.failure(error("User not valid"));

        TextView registerErrorText = (TextView) activity.findViewById(R.id.register_error_text);
        assertThat(registerErrorText).isVisible();
        assertThat(registerErrorText).hasTextString("User not valid");
    }

    private RetrofitError error(String message) {
        RetrofitError error = mock(RetrofitError.class);
        ApiError apiError = new ApiError(newArrayList(message));
        when(error.getBodyAs(ApiError.class)).thenReturn(apiError);
        return error;
    }

    private RegistrationActivity resumeRegistrationActivity() {
        return Robolectric.buildActivity(RegistrationActivity.class).create().resume().get();
    }

    private Intent intentFor(Class nextStartedActivity) {
        return new Intent(Robolectric.application, nextStartedActivity);
    }

    private void userIsRegistered() {
        User user = new User(1, "Test name");
        new UserInfoResource(Robolectric.application).save(user);
    }
}