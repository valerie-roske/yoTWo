package com.thoughtworks.yotwo.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.thoughtworks.yotwo.R;
import com.thoughtworks.yotwo.domain.User;
import com.thoughtworks.yotwo.persistance.UserInfoResource;


public class MainActivity extends Activity {

    private Button sendMessagesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessagesButton = (Button) findViewById(R.id.send_message_button);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ensureUserIsRegistered();

        sendMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ListUsersActivity.class));
            }
        });
    }

    private void ensureUserIsRegistered() {
        User user = new UserInfoResource(this).getUser();

        if (!user.exists()) {
            startActivity(new Intent(this, RegistrationActivity.class));
        }
    }
}
