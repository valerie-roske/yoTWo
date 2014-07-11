package com.thoughtworks.yotwo.api;

import com.thoughtworks.yotwo.domain.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;

public interface UserClient {
    @GET("/users.json")
    List<User> users();

    @POST("/users.json")
    User create(@Body User user);
}
