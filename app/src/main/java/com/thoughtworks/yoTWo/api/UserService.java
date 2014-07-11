package com.thoughtworks.yotwo.api;

import com.thoughtworks.yotwo.config.Config;
import com.thoughtworks.yotwo.domain.User;
import retrofit.RestAdapter;

import java.util.List;

public class UserService {

    private UserClient userClient;

    public UserService(Config config) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(config.valueFor("base_url"))
                .build();
        userClient = restAdapter.create(UserClient.class);
    }

    public List<User> users() { return userClient.users(); }

    public User create(User user) { return userClient.create(user); }
}
