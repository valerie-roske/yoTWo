package com.thoughtworks.easterEggHunt.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.thoughtworks.easterEggHunt.api.UserService;
import com.thoughtworks.easterEggHunt.domain.User;
import retrofit.RetrofitError;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class GetAllUsersTask extends AsyncTask<Void, Void, List<User>>{
    private AllUsersCallback allUsersCallback;
    private UserService userService;

    public GetAllUsersTask(AllUsersCallback allUsersCallback, UserService userService) {
        this.allUsersCallback = allUsersCallback;
        this.userService = userService;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        try {
            return userService.users();
        } catch (RetrofitError e) {
            Log.e(GetAllUsersTask.class.getName(), "Could not retrieve users", e);
        }
        return newArrayList();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        allUsersCallback.callback(users);
    }
}
