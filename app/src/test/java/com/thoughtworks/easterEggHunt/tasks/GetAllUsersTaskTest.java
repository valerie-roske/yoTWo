package com.thoughtworks.easterEggHunt.tasks;

import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import retrofit.RetrofitError;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class GetAllUsersTaskTest {

    @Test
    public void shouldReturnEmptyListWhenRetrofitFails() {
        UserService userService = mock(UserService.class);
        GetAllUsersTask task = new GetAllUsersTask(null, userService);
        when(userService.users()).thenThrow(RetrofitError.class);

        List<User> users = task.doInBackground();

        assertThat((ArrayList<User>) users, is(new ArrayList<User>()));
    }

    @Test
    public void shouldReturnUsersFromUserService() {
        ArrayList<User> users = newArrayList(new User(1, "Dude"));
        UserService userService = mock(UserService.class);
        GetAllUsersTask task = new GetAllUsersTask(null, userService);
        when(userService.users()).thenReturn(users);

        assertThat((ArrayList<User>) task.doInBackground(), is(users));
    }

}