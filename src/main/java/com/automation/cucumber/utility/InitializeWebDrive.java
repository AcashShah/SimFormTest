package com.automation.cucumber.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.automation.cucumber.NoSuitableDriverFoundException.NoSutiableDriverFoundException;
import com.automation.cucumber.configreader.PropertyFileReader;
import com.automation.cucumber.helper.configurationbrowser.BrowserType;
import com.automation.cucumber.helper.configurationbrowser.ChromeBrowser;
import com.automation.cucumber.settings.ObjectRepo;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class InitializeWebDrive {

	public InitializeWebDrive(PropertyFileReader reader) {
		ObjectRepo.reader = reader;
	}

	WebDriver driverIncong;

	public WebDriver standAloneStepUp(BrowserType bType) throws Exception {
		try {

			switch (bType) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				return chrome.getChromeDriver(chrome.getChromeCapabilities());

			default:
				throw new NoSutiableDriverFoundException(" Driver Not Found : " + ObjectRepo.reader.getBrowser());
			}
		} catch (Exception e) {
			// oLog.equals(e);
			throw e;
		}
	}

	@Before()
	public void beforeChrome() throws Exception {
		setUpDriver(ObjectRepo.reader.getBrowser());
	}

	@After()
	public void afterChrome(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
	}

	public void setUpDriver(BrowserType bType) throws Exception {
		ObjectRepo.driver = standAloneStepUp(bType);
		ObjectRepo.driver.manage().timeouts().pageLoadTimeout(ObjectRepo.reader.getPageLoadTimeOut(), TimeUnit.SECONDS);
		ObjectRepo.driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
		ObjectRepo.driver.manage().window().maximize();
	}

	public void tearDownDriver(Scenario scenario) throws Exception {

		try {
			if (ObjectRepo.driver != null) {

				if (scenario.isFailed())
				Thread.sleep(2000);
				isAlertPresent();
			
				tearDownBrowser();
			}
		} catch (Exception e) {
			tearDownBrowser();
			throw e;
		}
	}

	public void tearDownBrowser() {
		ObjectRepo.driver.quit();
		ObjectRepo.reader = null;
		ObjectRepo.driver = null;
	}

	public boolean isAlertPresent() {
		try {
			ObjectRepo.driver.switchTo().alert().accept();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

}
