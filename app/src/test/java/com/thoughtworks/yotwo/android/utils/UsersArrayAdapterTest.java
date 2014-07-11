package com.thoughtworks.yotwo.android.utils;

import android.view.View;
import android.widget.TextView;
import com.thoughtworks.yotwo.R;
import com.thoughtworks.yotwo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UsersArrayAdapterTest {

    @Test
    public void shouldRenderNameInListItemView() {
        List<User> users = newArrayList(new User(1, "Bill"));
        UsersArrayAdapter usersArrayAdapter = new UsersArrayAdapter(Robolectric.application, users);

        View listItemView = usersArrayAdapter.getView(0, null, null);
        TextView userTextView = (TextView) listItemView.findViewById(R.id.user_name);

        assertThat(userTextView).hasTextString("Bill");
    }

}