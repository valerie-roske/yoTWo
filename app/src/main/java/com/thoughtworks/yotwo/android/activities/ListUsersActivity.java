package com.thoughtworks.yotwo.android.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.thoughtworks.yotwo.R;
import com.thoughtworks.yotwo.android.utils.UsersArrayAdapter;
import com.thoughtworks.yotwo.api.RequestCallback;
import com.thoughtworks.yotwo.api.UserService;
import com.thoughtworks.yotwo.config.Config;
import com.thoughtworks.yotwo.config.Environment;
import com.thoughtworks.yotwo.domain.User;
import com.thoughtworks.yotwo.tasks.GetAllUsersTask;
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
    public void success(final List<User> users) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (users.isEmpty()) {
                    showEmptyText(R.string.no_users);
                    return;
                }

                ListAdapter listAdapter = new UsersArrayAdapter(ListUsersActivity.this, users);
                setListAdapter(listAdapter);
            }
        });
    }

    @Override
    public void failure(RetrofitError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showEmptyText(R.string.could_not_connect_to_server);
            }
        });
    }

    private void showEmptyText(int text) {
        TextView emptyView = (TextView) getListView().getEmptyView();
        emptyView.setText(text);
    }
}
