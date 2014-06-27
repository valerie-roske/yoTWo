package com.thoughtworks.easterEggHunt;

import com.thoughtworks.easterEggHunt.domain.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;

public interface UserClient {
    @GET("/users")
    List<User> users();

    @POST("/users")
    User create(@Body User user);
}