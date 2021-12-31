package com.automation.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = { "classpath:featurefile/Profile.feature" }, glue = {
		"classpath:com.automation.cucumber.stepdefinition",
		"classpath:com.automation.cucumber.utility", }, plugin = { "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/ProfileRunner.json"
		}, tags= "@Profile1",
		 monochrome = true
		)
public class ProfileRunner extends AbstractTestNGCucumberTests {

}
