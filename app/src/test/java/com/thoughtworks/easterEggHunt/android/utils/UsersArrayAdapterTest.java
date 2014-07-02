package com.thoughtworks.easterEggHunt.android.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UsersArrayAdapterTest {

    private static final String USER_1_NAME = "Bill";
    private Context context;

    @Test
    public void shouldRenderNameInListItemView() {
        context = Robolectric.application;
        List<User> users = newArrayList(new User(1, USER_1_NAME));
        UsersArrayAdapter usersArrayAdapter = new UsersArrayAdapter(context, users);

        View listItemView = usersArrayAdapter.getView(0, null, null);
        TextView userTextView = (TextView) listItemView.findViewById(R.id.user);

        assertThat(userTextView.getText().toString(), is(USER_1_NAME));
    }

}