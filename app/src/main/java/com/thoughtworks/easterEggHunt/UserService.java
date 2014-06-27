package com.thoughtworks.easterEggHunt;

import com.thoughtworks.easterEggHunt.domain.User;
import retrofit.RestAdapter;

import java.util.List;

public class UserService {

    private final RestAdapter restAdapter;
    private UserClient userClient;

    public UserService() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://easter-egg-hunt-api.herokuapp.com")
                .build();
        userClient = restAdapter.create(UserClient.class);
    }

    public List<User> users() { return userClient.users(); }

    public User create(User user) { return userClient.create(user); }
}
