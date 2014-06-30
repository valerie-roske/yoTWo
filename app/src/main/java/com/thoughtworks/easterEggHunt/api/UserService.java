package com.thoughtworks.easterEggHunt.api;

import com.thoughtworks.easterEggHunt.config.Config;
import com.thoughtworks.easterEggHunt.domain.User;
import retrofit.RestAdapter;

import java.util.List;

public class UserService {

    private final RestAdapter restAdapter;
    private UserClient userClient;

    public UserService(Config config) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(config.valueFor("base_url"))
                .build();
        userClient = restAdapter.create(UserClient.class);
    }

    public List<User> users() { return userClient.users(); }

    public User create(User user) { return userClient.create(user); }
}
