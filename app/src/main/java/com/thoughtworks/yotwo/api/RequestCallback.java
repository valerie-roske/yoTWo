package com.thoughtworks.yotwo.api;

import retrofit.RetrofitError;

public interface RequestCallback<T> {

    void success(T t);

    void failure(RetrofitError error);
}
