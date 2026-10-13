package com.petstay.runners;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "com.petstay")
@ConfigurationParameter(key = "cucumber.plugin",
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
public class PetStaySuite {
}
