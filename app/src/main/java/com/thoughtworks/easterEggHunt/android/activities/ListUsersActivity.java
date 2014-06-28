package com.thoughtworks.easterEggHunt.android.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.tasks.AllUsersCallback;
import com.thoughtworks.easterEggHunt.tasks.GetAllUsersTask;

import java.util.List;

public class ListUsersActivity extends ListActivity implements AllUsersCallback{

    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_all_users);

        new GetAllUsersTask(this, new UserService()).execute();
    }

    @Override
    public void callback(List<User> users) {
        listAdapter = new UsersArrayAdapter(this, R.layout.list_item, R.id.user, users);
        setListAdapter(listAdapter);
    }

    private class UsersArrayAdapter extends ArrayAdapter<User> {

        private List<User> users;

        public UsersArrayAdapter(Context context, int resource, int textViewResourceId, List<User> objects) {
            super(context, resource, textViewResourceId, objects);
            this.users = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView userView = (TextView) view.findViewById(R.id.user);

            userView.setText(users.get(position).getName());
            return view;
        }
    }
}
