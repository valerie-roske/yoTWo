package com.thoughtworks.easterEggHunt.config;

public class Environment {
    enum EnvironmentValue {
        DEV, PROD
    }

    public EnvironmentValue get() {
        return EnvironmentValue.DEV;
//        return EnvironmentValue.PROD; --> Switch the comments here to change to production env
    }
}
