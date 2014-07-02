package com.thoughtworks.easterEggHunt.android.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.android.utils.UsersArrayAdapter;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.config.Config;
import com.thoughtworks.easterEggHunt.config.Environment;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.tasks.AllUsersCallback;
import com.thoughtworks.easterEggHunt.tasks.GetAllUsersTask;

import java.util.List;

public class ListUsersActivity extends ListActivity implements AllUsersCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_users);

        Config config = new Config(this, new Environment());
        new GetAllUsersTask(this, new UserService(config)).execute();
    }

    @Override
    public void callback(List<User> users) {
        ListAdapter listAdapter = new UsersArrayAdapter(this, users);
        setListAdapter(listAdapter);
    }
}
