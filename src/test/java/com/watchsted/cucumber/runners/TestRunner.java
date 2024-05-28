package com.watchsted.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/watchsted/cucumber/features/",
        glue = "com/watchsted/cucumber/stepDefinitions",
        publish = true,
        monochrome = true,
        //dryRun = true,
        tags = "@001TC",

        plugin = {
                "pretty",
                "json:target/json_output/cucumber.json",
                "junit:target/junit_xml_output/cucumber.xml",
                "html:target/html_output/cucumber.html",
                "html:report/cucumber.html"
        })

public class TestRunner extends AbstractTestNGCucumberTests {
  /* @Override
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		return super.scenarios();
	}*/

}
