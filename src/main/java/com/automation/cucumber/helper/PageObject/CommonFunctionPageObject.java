package com.automation.cucumber.helper.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.cucumber.Textbox.TextBoxHelper;
import com.automation.cucumber.helper.Button.ButtonHelper;
import com.automation.cucumber.helper.JavaScript.JavaScriptHelper;
import com.automation.cucumber.helper.Scroll.Scroll;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;



public class CommonFunctionPageObject extends PageBase {
	
	private ButtonHelper btnHelper;
	private WaitHelper waitObj;
	private TextBoxHelper textBoxHelper;
	
	public CommonFunctionPageObject(WebDriver driver) {
		super(driver);
		btnHelper = new ButtonHelper(driver);
		textBoxHelper = new TextBoxHelper(driver);
		waitObj = new WaitHelper(driver, ObjectRepo.reader);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='login-email']")
	public WebElement usernameTxtBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='login-pwd']")
	public WebElement userPasswordTxtBox;
	
	@FindBy(how = How.XPATH, using = "//*[@ng-click='vm.login()']")
	public WebElement btnlogin;
	
	@FindBy(how = How.XPATH, using = "//input[@name='txtSecurityCode']")
	public WebElement otpOfTextareaField;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Validate')]")
	public WebElement btnValidate;
	
	
	public void inputUsername() throws Exception {
		waitObj.hardWait(5000);
//		new Scroll().scrollTillElem(usernameTxtBox);
//		waitObj.waitForElementVisible(usernameTxtBox);
		waitObj.elementExistAndVisibleelseSleep(usernameTxtBox, 30, 3000);
		textBoxHelper.sendKeys(usernameTxtBox, "Chintan");
		waitObj.hardWait(5000);
	}

	public void inputPassword() throws Exception {
		waitObj.hardWait(5000);
		waitObj.elementExistAndVisibleelseSleep(userPasswordTxtBox, 30, 3000);
		textBoxHelper.sendKeys(userPasswordTxtBox, "Simform@123");
		
	}
	
	public void clicktologin() throws Exception {
		waitObj.elementExistAndVisibleelseSleep(btnlogin, 30, 3000);
		btnHelper.click(btnlogin);
		waitObj.hardWait(8000);
	}
	
	public void validateButton() throws Exception {
		textBoxHelper.sendKeys(otpOfTextareaField, "123456");
		btnHelper.click(btnValidate);
		waitObj.hardWait(8000);
	}
	
	

}
