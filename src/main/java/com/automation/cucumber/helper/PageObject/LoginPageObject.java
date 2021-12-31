package com.automation.cucumber.helper.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.automation.cucumber.Textbox.TextBoxHelper;
import com.automation.cucumber.helper.Button.ButtonHelper;
import com.automation.cucumber.settings.ObjectRepo;


public class LoginPageObject extends PageBase
{
	private WebDriver driver;
	public ButtonHelper btnHelper;
	private TextBoxHelper textBoxHelper;

	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		btnHelper = new ButtonHelper(driver);
		textBoxHelper = new TextBoxHelper(driver);
	}

	/** WebElements **/

	@FindBy(how = How.XPATH, using = "//input[@ng-model='vm.user.username']")
	public WebElement usernameTxtBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='txtSecurityCode']")
	public WebElement otpOfTextareaField;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Invalid security code')]")
	public WebElement securityErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Validate')]")
	public WebElement btnValidate;
	
	@FindBy(how = How.XPATH, using = "//*[@ng-click='vm.toggelPasswordView()']")
	public WebElement eyeIconClick;

	public WebDriver getDriver() {
		return this.driver;
	}
	
	
	public void verifyLoginPass() throws Exception
	{
//		SoftAssert sf = new SoftAssert();
//		sf.assertEquals(dashboardLbl.getText(), "Dashboards");
//		sf.assertAll();
	}
	
	
	public void eyeClick() throws Exception
	{
		btnHelper.click(eyeIconClick);	
	}
	
	public void verifyErrorMessage() throws Exception
	{
		textBoxHelper.sendKeys(otpOfTextareaField, "1234567");
		btnHelper.click(btnValidate);
		Assert.assertEquals(textBoxHelper.getText(securityErrorMessage), "Invalid security code");
		textBoxHelper.clear(otpOfTextareaField);
	}
	
	
}
