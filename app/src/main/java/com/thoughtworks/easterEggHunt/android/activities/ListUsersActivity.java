package com.thoughtworks.easterEggHunt.android.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.tasks.AllUsersCallback;
import com.thoughtworks.easterEggHunt.tasks.GetAllUsersTask;

import java.util.ArrayList;
import java.util.List;

public class ListUsersActivity extends ListActivity implements AllUsersCallback{

    private List<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_all_users);

        new GetAllUsersTask(this, new UserService()).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        usersList = new ArrayList<>();
        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.user, usersList);
        setListAdapter(listAdapter);
    }

    @Override
    public void callback(List<User> users) {
        usersList.addAll(users);
    }
}
