package com.automation.cucumber.stepdefinition;

import com.automation.cucumber.helper.PageObject.CommonFunctionPageObject;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;



public class CommonFunctions {

	private CommonFunctionPageObject commonFnPageObject;
	private WaitHelper waitObj;
	public static String username=null, password=null;
	
	public CommonFunctions()
	{
		commonFnPageObject = new CommonFunctionPageObject(ObjectRepo.driver);
		waitObj = new WaitHelper(ObjectRepo.driver, ObjectRepo.reader);

	}
	
	@Given("Navigate to the URL")
	public void navigate_to_the_URL() throws Throwable {
		try {
			System.out.print(ObjectRepo.reader.getWebsite());
			ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to navigate");
		}
	}
	
	@When("Enter the given value and click on validate button")
	public void enter_given_value_and_click_on_validate_button() throws Throwable {
		commonFnPageObject.validateButton();
	}
	
	@When("Enter valid username in email or channel name")
	public void enter_valid_username_in_email_or_channel_name() throws Throwable {
		commonFnPageObject.inputUsername();
	}

	@When("Enter valid Password")
	public void enter_valid_Password() throws Throwable {
		commonFnPageObject.inputPassword();
	}
	
	@When("Click on the Login Button")
	public void click_on_the_Login_Button() throws Throwable {
			commonFnPageObject.clicktologin();
	}
	
	
}
