package com.thoughtworks.easterEggHunt.tasks;

import com.thoughtworks.easterEggHunt.api.RequestCallback;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import retrofit.RetrofitError;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class RegisterUserTaskTest {
    @Mock
    private UserService userService;
    @Mock
    private RequestCallback<User> requestCallback;

    private RegisterUserTask task;

    @Before
    public void setup() {
        initMocks(this);

        task = new RegisterUserTask(requestCallback, userService);
    }

    @Test
    public void shouldFailCallbackWhenRetrofitFails() {
        User user = new User("test name");
        when(userService.create(user)).thenThrow(RetrofitError.class);

        task.doInBackground(user);

        verify(requestCallback).failure(any(RetrofitError.class));
    }

    @Test
    public void shouldCallSuccessCallbackOnUserService() {
        User unsavedUser = new User("test name");
        User savedUser = new User(1, "test name");
        when(userService.create(unsavedUser)).thenReturn(savedUser);

        task.doInBackground(unsavedUser);

        verify(requestCallback).success(savedUser);
    }
}