package com.thoughtworks.yotwo.config;

import android.content.Context;
import android.content.res.Resources;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {
    private final Environment environment;
    private final Context context;
    private Map values;

    public Config(Context context, Environment environment) {
        this.context = context;
        this.environment = environment;
    }

    public String valueFor(String key) {
        return (String) values().get(key);
    }

    private Map values() {
        if( values == null ) {
            loadYamlFor(environment);
        }

        return values;
    }

    private void loadYamlFor(Environment environment) {
        Resources resources = context.getResources();
        int configResourceId = resources.getIdentifier(configFilenameFor(environment), "raw", context.getPackageName());

        InputStream rawConfig = resources.openRawResource(configResourceId);
        values = ((Map) new Yaml().load(rawConfig));
    }

    private String configFilenameFor(Environment environment) {
        return environment.get().name().toLowerCase() + "_config";
    }
}
