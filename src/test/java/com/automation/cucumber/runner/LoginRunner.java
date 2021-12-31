package com.automation.cucumber.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "classpath:featurefile/Login.feature" }, glue = {
		"classpath:com.automation.cucumber.stepdefinition",
		"classpath:com.automation.cucumber.utility", }, plugin = { "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/LoginRunner.json"
		}, tags= "@login1",
		 monochrome = true
		)
public class LoginRunner extends AbstractTestNGCucumberTests 
{
	
}
