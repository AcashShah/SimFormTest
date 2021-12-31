package com.automation.cucumber.Textbox;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.cucumber.helper.Generic.GenericHelper;
import com.automation.cucumber.helper.JavaScript.JavaScriptHelper;
import com.automation.cucumber.helper.Scroll.Scroll;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;


public class TextBoxHelper extends GenericHelper {
	
	private WebDriver driver;
	private GenericHelper geneHelpObj;
	private WaitHelper waitObj;
	private JavaScriptHelper jsHelper = new JavaScriptHelper(ObjectRepo.driver);
	WebDriverWait wait =new WebDriverWait (ObjectRepo.driver,60);
	
	public TextBoxHelper(WebDriver driver) {
		super(driver);
		this.driver = driver;
		geneHelpObj = new GenericHelper(driver); 
		waitObj = new WaitHelper(driver, ObjectRepo.reader);
	}

	public void sendKeysForUnique(WebElement locator, String value){
		waitObj.waitForElementVisible(locator);
		waitObj.waitForElementClickable(locator);
		new Scroll().scrollTillElem(locator);
		for (int i = 0; i < value.length(); i++){
			getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))).sendKeys(String.valueOf(value.charAt(i)));
		}
	}

	public void sendKeys(WebElement locator, String value) {
		waitObj.waitForElementVisible(locator);
		waitObj.waitForElementClickable(locator);
		locator.clear();
		new Scroll().scrollTillElem(locator);
		getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))).sendKeys(value);
	}

	public void TypeInField(WebElement locator, String value) {
		waitObj.waitForElementVisible(locator);
		waitObj.waitForElementClickable(locator);
		WebElement element = getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator)));
		element.clear();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
		}
	}
	
	public void clear(WebElement locator) {
		
		waitObj.waitForElementVisible(locator);
		getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))).clear();
	}

	public String getText(WebElement locator) {
		waitObj.waitForElementVisible(locator);
		new Scroll().scrollTillElem(locator);
		return getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))).getText();
	}

	public void clearAndSendKeys(WebElement locator, String value) {
		waitObj.waitForElementVisible(locator);
		WebElement element = getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator)));
		element.clear();
		element.sendKeys(value);	
	}
	
	public boolean isEnabled(WebElement locator) {
		
		waitObj.waitForElementVisible(locator);
		return getElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))).isEnabled();
	}
	
	public void enableElement(WebElement element) {
	
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("arguments[0].disabled = false", element);
		
	}

	public String getDisableTextboxVal(WebElement locator) throws Exception
	{
		waitObj.waitForElementVisible(locator);
		new Scroll().scrollTillElem(locator);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('disabled','disabled')",locator);
		jsHelper.javascriptClick(locator);
		String str= locator.getAttribute("value");
	
		if(str.isEmpty())
		{
			str="blank value";
		}
		return str;
	}
	
	public String getListToString(List<WebElement> locator) {
		/*
		 * Function : This function will return list of element in form of string with comma separated.
		 * Created By : Santosh Shinde Created Date : 11th march 2020
		 */
		int count = 0; String str = "";
		
		for (WebElement element : locator) {
			if (locator.size() > 0) {
				String myText = locator.get(count).getText();
				
				System.out.println("This is availabel String :: " + myText);
				
				count++;
				str = str + "," + myText;
			}
		}
		
		String wholeStr = str.substring(1);
		System.out.println("This is Final String :: " + wholeStr);
		return wholeStr;
	}
	
	public String getPlaceholderText(WebElement ele) {
		System.out.println("placeholder value is::"+ele.getAttribute("placeholder"));
		return ele.getAttribute("placeholder");
	}
	
	public boolean isClicked(WebElement element)
	{ 
	    try {
	        WebDriverWait wait = new WebDriverWait(ObjectRepo.driver, 5);
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	        return true;
	    } catch(Exception e){
	        return false;
	    }
	}
	
	public void txtboxClicked(WebElement element)
	{  
	  wait.until(ExpectedConditions.elementToBeClickable(element));
	  element.click();  
	}

}
