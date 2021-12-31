package com.automation.cucumber.configreader;

import java.io.FileReader;
import java.util.Properties;

import com.automation.cucumber.helper.configurationbrowser.BrowserType;
import com.automation.cucumber.interfaces.IconfigReader;
import com.automation.cucumber.utility.ResourceHelper;




public class PropertyFileReader implements IconfigReader {
	
	private Properties prop = null;

	public PropertyFileReader() {
		FileReader reader;
		prop = new Properties();
		try {
			reader = new FileReader("src/main/resources/configfile/config.properties");
			prop.load(reader);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	public long getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}
	
	public int getTimeOutInSeconds() {
		return Integer.parseInt(prop.getProperty("timeOutInSeconds"));
	}
	
	public int getPollingEveryInMiliSec() {
		return Integer.parseInt(prop.getProperty("pollingEveryInMiliSec"));
	}
	
	public String getWebsite() {
		return prop.getProperty("Website");
	}
	
	public long getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}
	
	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}


}
