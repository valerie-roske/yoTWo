package com.thoughtworks.yotwo.api;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ApiError {
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public String messages() {
        return StringUtils.join(errors.toArray(new String[errors.size()]), ", ");
    }
}
