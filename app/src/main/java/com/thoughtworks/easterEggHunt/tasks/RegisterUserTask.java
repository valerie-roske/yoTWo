package com.thoughtworks.easterEggHunt.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.thoughtworks.easterEggHunt.api.RequestCallback;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;
import retrofit.RetrofitError;

public class RegisterUserTask extends AsyncTask<User, Void, Void> {
    private final RequestCallback<User> registrationCallback;
    private final UserService userService;

    public RegisterUserTask(RequestCallback<User> registrationCallback, UserService userService) {
        this.registrationCallback = registrationCallback;
        this.userService = userService;
    }

    @Override
    protected Void doInBackground(User... users) {
        try {
            User savedUser = userService.create(users[0]);
            registrationCallback.success(savedUser);
        } catch (RetrofitError e) {
            Log.e(RegisterUserTask.class.getName(), "Could not register user", e);
            registrationCallback.failure(e);
        }
        return null;
    }
}
