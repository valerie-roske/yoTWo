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

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class GetAllUsersTaskTest {

    @Mock
    private UserService userService;
    @Mock
    private RequestCallback<List<User>> requestCallback;

    private GetAllUsersTask task;

    @Before
    public void setup() {
        initMocks(this);

        task = new GetAllUsersTask(requestCallback, userService);
    }

    @Test
    public void shouldFailCallbackWhenRetrofitFails() {
        when(userService.users()).thenThrow(RetrofitError.class);

        task.doInBackground();

        verify(requestCallback).failure(any(RetrofitError.class));
    }

    @Test
    public void shouldCallSuccessCallbackOnUserService() {
        ArrayList<User> users = newArrayList(new User(1, "Dude"));
        when(userService.users()).thenReturn(users);

        task.doInBackground();

        verify(requestCallback).success(users);
    }

}