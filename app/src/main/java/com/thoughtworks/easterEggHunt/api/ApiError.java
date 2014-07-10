package com.thoughtworks.easterEggHunt.api;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ApiError {
    private List<String> errors;

    public String messages() {
        return StringUtils.join(errors.toArray(new String[0]), ", ");
    }
}
