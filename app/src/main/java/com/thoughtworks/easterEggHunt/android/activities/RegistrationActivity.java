package com.thoughtworks.easterEggHunt.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.api.ApiError;
import com.thoughtworks.easterEggHunt.api.RequestCallback;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.config.Config;
import com.thoughtworks.easterEggHunt.config.Environment;
import com.thoughtworks.easterEggHunt.domain.User;
import com.thoughtworks.easterEggHunt.persistance.UserInfoResource;
import com.thoughtworks.easterEggHunt.tasks.RegisterUserTask;
import retrofit.RetrofitError;

public class RegistrationActivity extends Activity implements RequestCallback<User> {

    private Button registerButton;
    private EditText registerNameText;
    private TextView registrationErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerButton = (Button) findViewById(R.id.register_button);
        registerNameText = (EditText) findViewById(R.id.register_name_text);
        registrationErrorText = (TextView) findViewById(R.id.register_error_text);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ensureUserIsNotRegistered();

        registerButton.setOnClickListener(new RegisterUserClickListener(this));
    }

    @Override
    public void success(User user) {
        new UserInfoResource(this).save(user);

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void failure(final RetrofitError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ApiError apiError = (ApiError) error.getBodyAs(ApiError.class);

                registrationErrorText.setText(apiError.messages());
                registrationErrorText.setVisibility(View.VISIBLE);
            }
        });
    }

    private void ensureUserIsNotRegistered() {
        User user = new UserInfoResource(this).getUser();
        if (user.exists()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private class RegisterUserClickListener implements View.OnClickListener {

        private final RequestCallback<User> registrationCallback;

        public RegisterUserClickListener(RequestCallback<User> registrationCallback) {
            this.registrationCallback = registrationCallback;
        }

        @Override
        public void onClick(View view) {
            String name = registerNameText.getText().toString();
            User user = new User(name);

            Config config = new Config(view.getContext(), new Environment());
            new RegisterUserTask(registrationCallback, new UserService(config)).execute(user);
        }
    }
}
