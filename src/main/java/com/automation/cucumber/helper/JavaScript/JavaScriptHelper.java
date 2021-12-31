package com.automation.cucumber.helper.JavaScript;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.cucumber.interfaces.IwebComponent;

public class JavaScriptHelper implements IwebComponent  {
	
	
	private WebDriver driver;
	public JavaScriptHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}
	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])",
				element.getLocation().x, element.getLocation().y);
	}

	public void scrollToElemet(By locator) {
		scrollToElemet(driver.findElement(locator));
	}

	public void scrollToElemetAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		scrollToElemet(element);
		element.click();
	}
	public void scrollToElemetAndClick(WebElement element) 
	{
		scrollToElemet(element);
		element.click();
	}
	public void scrollIntoView(WebElement element) 
	{
		executeScript("arguments[0].scrollIntoView()", element);
	}
	public void scrollIntoView(By locator) 
	{
		scrollIntoView(driver.findElement(locator));
	}
	public void scrollIntoViewAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		scrollIntoView(element);
		element.click();
	}
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
	}
	public void javascriptClick(WebElement element) {
		scrollIntoView(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	public void sendJavascript(WebElement element, String inputValue) {
		WebElement webl = element;
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].value=inputValue;", webl);
//		JavascriptExecutor js = (JavascriptExecutor)driver;		
		  js.executeScript("arguments[0].value='"+inputValue+"';", webl);
		  element.sendKeys(Keys.TAB);	
	}
	public void enableDisabledField(WebElement element) {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('disabled','disabled')",element);
	}

}
