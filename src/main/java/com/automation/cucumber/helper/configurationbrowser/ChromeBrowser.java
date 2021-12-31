package com.automation.cucumber.helper.configurationbrowser;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.cucumber.utility.DriverPathUtility;

public class ChromeBrowser {
	
	public Capabilities getChromeCapabilities() 
	{
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--no-sandbox"); // Bypass OS security model
		option.addArguments("--start-maximized"); // open Browser in maximized mode
		option.addArguments("disable-infobars"); // disabling infobars
		option.addArguments("--disable-extensions"); // disabling extensions
		option.addArguments("--disable-gpu"); // applicable to windows os only
		option.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		return option;
	}
	
	public WebDriver getChromeDriver(Capabilities cap) 
	{

		System.setProperty("webdriver.chrome.driver",DriverPathUtility.ChromeDriver);
		System.setProperty("java.awt.headless", "false");
		return new ChromeDriver(cap);
	}
	
	public WebDriver getChromeDriver(String hubUrl,Capabilities cap) throws MalformedURLException 
	{
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}


}
