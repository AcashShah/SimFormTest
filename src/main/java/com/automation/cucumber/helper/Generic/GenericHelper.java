package com.automation.cucumber.helper.Generic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.cucumber.helper.Scroll.Scroll;
import com.automation.cucumber.settings.ObjectRepo;


public class GenericHelper {
	
	private WebDriver driver;
	public static String chromeLINUXInst;
	public GenericHelper(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement getElement(By locator) {
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);

		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Check for element is present based on locator
	 * If the element is present return the web element otherwise null
	 * @param locator
	 * @return WebElement or null
	 */

	public WebElement getElementWithNull(By locator) {
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {

		boolean flag = driver.findElements(locator).size() >= 1;
		return flag;
	}


	public byte[] takeScreenShotBytes() {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		//		 byte[] screenShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
		return screenshot;
	}

	public String getChromeLINUXInst(WebElement element) {
		chromeLINUXInst = element.toString().substring(33 , 65);
		System.err.println("getChromeLINUXInst :::"+ chromeLINUXInst);
		return chromeLINUXInst;
	}
	
	public String createLocatorFromWebElement(WebElement element) {
		String temp = element.toString().split("By.")[1];
		String locator =  temp.substring(0, temp.length()-1);
		return locator;
	}
	
	public String returnLocator(WebElement element) {
		String locator= "[[ChromeDriver: chrome on LINUX ("+chromeLINUXInst+")] -> " +createLocatorFromWebElement(element)+"]";
		
		return locator;
	}
	
	// split the webelement when found in page
	public String getLocatorFromWebElement(WebElement element) {
		String locator = null;
		try {
		locator = element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
		} catch (Exception e) {
			//This called for wait element 
			locator = returnLocator(element).toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
			System.out.println("Final locator = " + locator);
		} 
		return locator;
	}
	public By locatorParser(String locator) {

		By loc = By.id(locator);
		if (locator.contains("id:")) {
			System.err.println("This is ID: " + locator);
			locator = locator.replace("id: ", "");
			loc = By.id(locator);
			
		} else if (locator.contains("name:")) {
			System.err.println("This is NAME: " + locator);
			locator = locator.replace("name: ", "");
			loc = By.name(locator);

		} else if (locator.contains("xpath:")) {
			locator = locator.replace("xpath: ", "");
			loc = By.xpath(locator);
		} else if (locator.contains("css selector:")) {

			System.err.println("This is CSS: " + locator);
			locator = locator.replace("css selector: ", "");
			loc = By.cssSelector(locator);

		} else if (locator.contains("link text:")) {
			System.err.println("This is link text: " + locator);
			locator = locator.replace("link text: ", "");
			loc = By.linkText(locator.trim());
		}

		return loc;

	}

	public boolean isDisplayed(WebElement locator) {
		new Scroll().scrollTillElem(locator);
		return locator.isDisplayed();
	}

	public String getCurrentTimeAndDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now).toString();
	}

	public String getTodayDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now).toString();
	}

	public int getCellingValue(int upperValue, int lowerValue) {
		int n = (int) Math.ceil((double) upperValue / lowerValue)	;;
		return n;
	}

	public String getCurrentSystemDate() {
		/*
		 * Function : This function is used to display current system date : Created By
		 * : Santosh Shinde Created Date : 23th oct 2018
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now).toString();
	}
	
	public String getCurrentMonth() {
		/*
		 * Function : This function is used to display current system date : Created By
		 * : Santosh Shinde Created Date : 23th oct 2018
		 */
		String[] monthName = {"January", "February",
	               "March", "April", "May", "June", "July",
	               "August", "September", "October", "November",
	               "December"};
	       Calendar cal = Calendar.getInstance();
	       String month = monthName[cal.get(Calendar.MONTH)];
	       System.out.println("Month name: " + month);
		
		return month;
	}
	
	public boolean isElementPresent(WebElement element)
	{
		boolean flag = false;
		
		By tempxpath = locatorParser(getLocatorFromWebElement(element));

		if(ObjectRepo.driver.findElements(tempxpath).size()!=0) 
		{        

			if(ObjectRepo.driver.findElement(tempxpath).isDisplayed()== true)
			{
				new Scroll().scrollTillElem(element);
				flag=true;

			}else {
				System.out.println("Element is present but not displayed");
			}
		}else {
			System.out.println("Element is not present");
		}
		return flag;  
	}

	public String getAttributevalue(WebElement ele,String attributename)
	{
		String attributeval=null;
		if(isElementPresent(ele)==true)
		{
			new Scroll().scrollTillElem(ele);
			attributeval = ele.getAttribute(attributename);
		}
		return attributeval;
	}
	public boolean checkDisableTextbox(WebElement ele) throws Exception
	{
		boolean attributeval;
		if(isElementPresent(ele)==true)
		{
			attributeval = ele.isEnabled();
		}
		else
		{
			attributeval=true;
		}
		return attributeval;
	}

	public boolean isElementPresentByLocator(By locator)
	{
		boolean flag = false;


		if(ObjectRepo.driver.findElements(locator).size()!=0) 
		{        

			if(ObjectRepo.driver.findElement(locator).isDisplayed()== true)
			{
				new Scroll().scrollTillElem(driver.findElement(locator));
				flag=true;

			}else {
				System.out.println("Element is present but not displayed");
			}
		}else {
			System.out.println("Element is not present");
		}
		return flag;  
	}
	
	public String getNullValuesFormTextBox(WebElement element) {
		new Scroll().scrollTillElem(element);
		String nullValue = element.getAttribute("value");
		return nullValue;
	}

	public void changeTab(int tabNo) {
		ArrayList tabs = new ArrayList (driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window((String) tabs.get(tabNo));
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEnableBtn(WebElement element) throws Exception
	{
		boolean attributeval;
		if(isElementPresent(element)==true)
		{
			attributeval = element.isEnabled();
		}
		else
		{
			attributeval=false;
		}
		return attributeval;
	}

}
