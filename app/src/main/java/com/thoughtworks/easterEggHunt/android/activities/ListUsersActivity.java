package com.thoughtworks.easterEggHunt.android.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.android.utils.UsersArrayAdapter;
import com.thoughtworks.easterEggHunt.api.RequestCallback;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.config.Config;
import com.thoughtworks.easterEggHunt.config.Environment;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.tasks.GetAllUsersTask;
import retrofit.RetrofitError;

import java.util.List;

public class ListUsersActivity extends ListActivity implements RequestCallback<List<User>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_users);

        Config config = new Config(this, new Environment());
        new GetAllUsersTask(this, new UserService(config)).execute();
    }

    @Override
    public void success(List<User> users) {
        ListAdapter listAdapter = new UsersArrayAdapter(this, users);
        setListAdapter(listAdapter);
    }

    @Override
    public void failure(RetrofitError error) {
        TextView emptyView = (TextView) getListView().getEmptyView();
        emptyView.setText(R.string.could_not_connect_to_server);
    }
}
