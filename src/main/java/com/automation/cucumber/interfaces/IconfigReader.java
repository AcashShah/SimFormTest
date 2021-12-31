package com.automation.cucumber.interfaces;

import com.automation.cucumber.helper.configurationbrowser.BrowserType;

public interface IconfigReader {
	
	public long getImplicitWait();
	public int getTimeOutInSeconds();
	public int getPollingEveryInMiliSec();
	public String getWebsite();
	public long getPageLoadTimeOut();
	public BrowserType getBrowser();
}
