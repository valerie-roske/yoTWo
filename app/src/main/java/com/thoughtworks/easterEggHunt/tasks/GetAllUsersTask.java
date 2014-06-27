package com.thoughtworks.easterEggHunt.tasks;

import android.os.AsyncTask;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;

import java.util.List;

public class GetAllUsersTask extends AsyncTask<Void, Void, List<User>>{
    private AllUsersCallback allUsersCallback;
    private UserService userService;

    public GetAllUsersTask(AllUsersCallback allUsersCallback, UserService userService) {
        this.allUsersCallback = allUsersCallback;
        this.userService = userService;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        return userService.users();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        allUsersCallback.callback(users);
    }
}
