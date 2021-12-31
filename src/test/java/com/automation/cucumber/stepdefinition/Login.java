package com.automation.cucumber.stepdefinition;

import com.automation.cucumber.helper.NavigationHelper;
import com.automation.cucumber.helper.PageObject.CommonFunctionPageObject;
import com.automation.cucumber.helper.PageObject.LoginPageObject;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
	
	private LoginPageObject lpage;
	private CommonFunctionPageObject commonFnPageObject;
	private WaitHelper waitObj;
	private NavigationHelper navigartionHelper;
	
	
	public Login() {
		lpage = new LoginPageObject(ObjectRepo.driver);
		commonFnPageObject = new CommonFunctionPageObject(ObjectRepo.driver);
		waitObj = new WaitHelper(ObjectRepo.driver, ObjectRepo.reader);
		navigartionHelper = new NavigationHelper(ObjectRepo.driver);
	}
	
	@When("Get current url after navigation and compare with actual given url")
	public void get_current_url_after_navigation_and_compare_with_actual_given_url() throws Throwable {
		String changeable_URL = navigartionHelper.getCurrentUrl();
		System.out.print(changeable_URL);
		String actual_URL = ObjectRepo.reader.getWebsite();
		System.out.print(actual_URL);
		
//		SoftAssert sf = new SoftAssert();
//		sf.assertEquals(changeable_URL, actual_URL);
//		sf.assertAll();
	}
	
	@Then("Enter the given value and validate error message")
	public void enter_the_given_value_and_validate_error_message() throws Throwable {
		lpage.verifyErrorMessage();
		
	}
	
	@When("Click on the eye icon to make your password visible")
	public void click_on_the_eye_icon_to_make_your_password_vivisble() throws Throwable {
		lpage.eyeClick();
	}

	@Then("Verify that the user is able to login successfully into the system")
	public void verify_that_the_user_is_able_to_login_successfully_into_the_system() throws Throwable {
	    lpage.verifyLoginPass();
	}
	

}
