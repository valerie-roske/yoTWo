package com.thoughtworks.easterEggHunt.domain;

public class User {
    public static final Integer MISSING_ID = -1;

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean exists() {
        return !MISSING_ID.equals(id);
    }
}
