package com.thoughtworks.easterEggHunt.android.activities;

import android.widget.ListAdapter;
import com.thoughtworks.easterEggHunt.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ListUsersActivityTest {

    public static final String USER_1_NAME = "Ryan";
    public static final String USER_2_NAME = "Jered";

    @Test
    public void shouldPopulateListAdapterWithUsers() {
        User expectedUser1 = new User(1, USER_1_NAME);
        User expectedUser2 = new User(2, USER_2_NAME);

        ListUsersActivity activity = setupActivityWithSuccessfulCallback(expectedUser1, expectedUser2);

        ListAdapter listAdapter = activity.getListAdapter();

        User actualUser1 = (User) listAdapter.getItem(0);
        User actualUser2 = (User) listAdapter.getItem(1);

        assertThat(actualUser1, is(expectedUser1));
        assertThat(actualUser2, is(expectedUser2));
    }


    private ListUsersActivity setupActivityWithSuccessfulCallback(User expectedUser1, User expectedUser2) {
        ListUsersActivity activity = Robolectric.buildActivity(ListUsersActivity.class).create().get();
        ArrayList<User> users = newArrayList(expectedUser1, expectedUser2);
        activity.callback(users);
        return activity;
    }
}