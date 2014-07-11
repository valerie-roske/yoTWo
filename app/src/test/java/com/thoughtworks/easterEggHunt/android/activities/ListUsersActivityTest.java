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

        ListUsersActivity activity = createListUsersActivity();
        activity.success(users);

        ListAdapter listAdapter = activity.getListAdapter();
        assertThat(listAdapter).hasItem(expectedUser1, 0);
        assertThat(listAdapter).hasItem(expectedUser2, 1);
    }

    @Test
    public void shouldShowEmptyTextWhenThereAreNoUsers() throws Exception {
        ListUsersActivity activity = createListUsersActivity();
        activity.success(new ArrayList<User>());

        TextView emptyView = (TextView) activity.getListView().getEmptyView();
        assertThat(emptyView).hasText(R.string.no_users);
    }

    @Test
    public void shouldHaveErrorMessageOnPageWhenRequestFails() {
        ListUsersActivity activity = createListUsersActivity();
        activity.failure(mock(RetrofitError.class));

        TextView emptyView = (TextView) activity.getListView().getEmptyView();
        assertThat(emptyView).hasText(R.string.could_not_connect_to_server);
    }

    private ListUsersActivity createListUsersActivity() {
        return Robolectric.buildActivity(ListUsersActivity.class).create().get();
    }
}