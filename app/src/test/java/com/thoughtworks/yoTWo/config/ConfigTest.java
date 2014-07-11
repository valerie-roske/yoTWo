package com.thoughtworks.yotwo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ConfigTest {
    @Test
    public void shouldGetDevValues() throws Exception {
        Environment environment = buildEnvironmentFor(Environment.EnvironmentValue.DEV);

        Config config = new Config(Robolectric.application, environment);

        assertThat(config.valueFor("base_url"), containsString("<insert your public IP here>"));
    }

    @Test
    public void shouldGetProductionValues() throws Exception {
        Environment environment = buildEnvironmentFor(Environment.EnvironmentValue.PROD);

        Config config = new Config(Robolectric.application, environment);

        assertThat(config.valueFor("base_url"), is("http://yo-two-api.herokuapp.com"));
    }

    private Environment buildEnvironmentFor(Environment.EnvironmentValue env) {
        Environment environment = mock(Environment.class);
        when(environment.get()).thenReturn(env);
        return environment;
    }
}
