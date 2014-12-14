package com.thoughtworks.yotwo.android.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.thoughtworks.yotwo.R;
import com.thoughtworks.yotwo.android.activities.RecordActivity;
import com.thoughtworks.yotwo.domain.User;

import java.util.List;

public class UsersArrayAdapter extends ArrayAdapter<User> {

    public UsersArrayAdapter(Context context, List<User> users) {
        super(context, R.layout.list_item);
        this.addAll(users);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item, parent, false);
        }

        final TextView userTextView = (TextView) convertView.findViewById(R.id.user_name);
        userTextView.setText(getItem(position).getName());

        final View finalConvertView = convertView;
        userTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalConvertView.getContext(), RecordActivity.class);
                intent.putExtra("userid", getItem(position).getId());
//                startActivity(intent);
            }
        });

        return convertView;
    }


}