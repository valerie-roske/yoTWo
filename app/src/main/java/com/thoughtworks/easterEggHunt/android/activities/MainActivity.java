package com.thoughtworks.easterEggHunt.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.persistance.UserInfoResource;
import org.apache.commons.lang3.StringUtils;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserInfoResource userInfoResource = new UserInfoResource(this);
        String name = userInfoResource.getName();

        if(StringUtils.isBlank(name)) {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button sendMessagesButton = (Button) findViewById(R.id.send_message_button);

        sendMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListUsersActivity.class);
                startActivity(intent);
            }
        });
    }
}
