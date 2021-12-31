package com.automation.cucumber.helper;

import java.net.URL;

import org.openqa.selenium.WebDriver;

public class NavigationHelper {

	private WebDriver driver;

	public NavigationHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void navigateTo(String url) {
		driver.get(url);
	}

	public void naviagteTo(URL url) {
		driver.get(url.getPath());
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
}
