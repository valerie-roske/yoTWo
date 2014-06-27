package com.thoughtworks.easterEggHunt.tasks;

import com.thoughtworks.easterEggHunt.domain.User;

import java.util.List;

public interface AllUsersCallback {
    void callback(List<User> users);
}
