package com.automation.cucumber.stepdefinition;


import com.automation.cucumber.helper.PageObject.CommonFunctionPageObject;
import com.automation.cucumber.helper.PageObject.ProfilePageObject;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;


import io.cucumber.java.en.Then;

public class Profile {

	
	private ProfilePageObject myProfilePage;
	private CommonFunctionPageObject commonFnPageObject;
	private WaitHelper waitObj;
	
	public Profile() {
		myProfilePage = new ProfilePageObject(ObjectRepo.driver);
		commonFnPageObject = new CommonFunctionPageObject(ObjectRepo.driver);
		waitObj = new WaitHelper(ObjectRepo.driver, ObjectRepo.reader);

	}
	
	@Then("Click on the Settings Icon on your Right Top Corner and Select Edit Profile Option")
	public void enter_the_given_value_and_validate_error_message() throws Throwable {		
		waitObj.hardWait(2000);
		myProfilePage.clickSettingIcon();
		waitObj.hardWait(5000);
		myProfilePage.clickEdirProfile();
		waitObj.hardWait(5000);
	}
	
	@Then("Read all the Data in the Cover Pic area given")
	public void eread_all_data_in_cover_pic_Area_given() throws Throwable {	
		myProfilePage.allData();
	}
	
	@Then("Click on the Dashboard")
	public void click_on_dashboard() throws Throwable {	
		myProfilePage.clickDashboard();
	}
	
	@Then("Click on the Post Section")
	public void click_on_post_selection() throws Throwable {
		myProfilePage.postSection();
	}
	
	@Then("Select Photo from Local Storage and Click on Post Button")
	public void select_photo_from_local_storge_and_click_on_post_button() throws Throwable {
		myProfilePage.clickOnPhotoandSend();
	}
	
	@Then("Click on the Comment Option")
	public void click_on_comment_option() throws Throwable {	
		myProfilePage.clickOnComment();
	}
	
	@Then("post the comment")
	public void post_commnet() throws Throwable {
		myProfilePage.postComment();
		
	}
	
	
	
	
	
	
	
	
	
}
