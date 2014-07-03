package com.thoughtworks.easterEggHunt.android.activities;

import android.widget.ListAdapter;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import retrofit.RetrofitError;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class ListUsersActivityTest {

    public static final String USER_1_NAME = "Ryan";
    public static final String USER_2_NAME = "Jered";

    @Test
    public void shouldPopulateListAdapterWithUsers() {
        User expectedUser1 = new User(1, USER_1_NAME);
        User expectedUser2 = new User(2, USER_2_NAME);
        ArrayList<User> users = newArrayList(expectedUser1, expectedUser2);

        ListUsersActivity activity = getListUsersActivity();
        activity.success(users);

        ListAdapter listAdapter = activity.getListAdapter();
        User actualUser1 = (User) listAdapter.getItem(0);
        User actualUser2 = (User) listAdapter.getItem(1);

        assertThat(actualUser1, is(expectedUser1));
        assertThat(actualUser2, is(expectedUser2));
    }

    @Test
    public void shouldHaveCorrectMessageOnPageWhenRequestFails() {
        ListUsersActivity activity = getListUsersActivity();

        activity.failure(mock(RetrofitError.class));

        TextView emptyView = (TextView) activity.getListView().getEmptyView();
        assertThat(emptyView).hasText(R.string.could_not_connect_to_server);
    }

    private ListUsersActivity getListUsersActivity() {
        return Robolectric.buildActivity(ListUsersActivity.class).create().get();
    }
}