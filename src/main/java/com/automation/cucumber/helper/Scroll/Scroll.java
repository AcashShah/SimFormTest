package com.automation.cucumber.helper.Scroll;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.cucumber.settings.ObjectRepo;

public class Scroll {
	
public void scrollDown(WebDriver driver) {
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	public void scrollUp(WebDriver driver) {
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void scrollTillElem(WebElement element) {
		((JavascriptExecutor) ObjectRepo.driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
