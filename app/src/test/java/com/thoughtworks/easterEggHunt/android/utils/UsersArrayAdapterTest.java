package com.thoughtworks.easterEggHunt.android.utils;

import android.view.LayoutInflater;
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
import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UsersArrayAdapterTest {

    @Test
    public void shouldRenderNameInListItemView() {
        List<User> users = newArrayList(new User(1, "Bill"));
        View convertView = LayoutInflater.from(Robolectric.application).inflate(R.layout.list_item, null, false);
        UsersArrayAdapter usersArrayAdapter = new UsersArrayAdapter(Robolectric.application, users);

        View listItemView = usersArrayAdapter.getView(0, convertView, null);
        TextView userTextView = (TextView) listItemView.findViewById(R.id.user_name);

        assertThat(userTextView).hasTextString("Bill");
    }

}