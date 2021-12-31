package com.automation.cucumber.helper.Button;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import com.automation.cucumber.helper.Scroll.Scroll;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;
import com.automation.cucumber.utility.Helper;


public class ButtonHelper  {
	
	private WebDriver driver;
	private WaitHelper waitObj;
	public Scroll scroll = new Scroll();	
	public ButtonHelper(WebDriver driver)
	{
		this.driver = driver;
		waitObj = new WaitHelper(driver, ObjectRepo.reader);
	}
	public void click(By locator) {
		click(driver.findElement(locator));
	}

	public void click(WebElement element){
		waitObj.waitForElementVisible(element);
		waitObj.waitForElementClickable(element);
		scroll.scrollTillElem(element);
		new Helper().javascriptExecutorClick(element);
//		element.click();
	}
	
	public String BtnColor(WebElement element) 
	{
		waitObj.waitForElementVisible(element);
		scroll.scrollTillElem(element);
		String color = element.getCssValue("background-color");
		System.out.println(color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return hex;
	}
	
	public String FontColorCodeHex(WebElement element) {
		waitObj.waitForElementVisible(element);
		scroll.scrollTillElem(element);
		String color = element.getCssValue("color");
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return hex;
	}
	
	public String FontSize(WebElement element) {
		waitObj.waitForElementVisible(element);
		scroll.scrollTillElem(element);
		String fontSize = element.getCssValue("font-size");
		return fontSize;
	}
	public String FontStyle(WebElement element) {
		waitObj.waitForElementVisible(element);
		scroll.scrollTillElem(element);
		String fontStyle = element.getCssValue("font-family");
		return fontStyle;
	}

}
