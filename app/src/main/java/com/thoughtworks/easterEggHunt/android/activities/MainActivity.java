package com.thoughtworks.easterEggHunt.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
}
