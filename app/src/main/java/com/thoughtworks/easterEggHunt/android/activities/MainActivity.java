package com.thoughtworks.easterEggHunt.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.persistance.UserInfoResource;


public class MainActivity extends Activity {

    private Button sendMessagesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessagesButton = (Button) findViewById(R.id.send_message_button);

        UserInfoResource userInfoResource = new UserInfoResource(this);
        String name = userInfoResource.getName();

//        if(StringUtils.isBlank(name)) {
//            Intent intent = new Intent(this, RegistrationActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        sendMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListUsersActivity.class);
                startActivity(intent);
            }
        });
    }
}
