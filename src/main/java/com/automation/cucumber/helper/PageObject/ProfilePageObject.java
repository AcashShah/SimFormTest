package com.automation.cucumber.helper.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.JavascriptExecutor;
import com.automation.cucumber.Textbox.TextBoxHelper;
import com.automation.cucumber.helper.Button.ButtonHelper;
import com.automation.cucumber.helper.JavaScript.JavaScriptHelper;
import com.automation.cucumber.helper.Scroll.Scroll;
import com.automation.cucumber.helper.Wait.WaitHelper;
import com.automation.cucumber.settings.ObjectRepo;

public class ProfilePageObject extends PageBase {

	private ButtonHelper btnHelper;
	private WaitHelper waitObj;
	private TextBoxHelper textBoxHelper;
	private JavaScriptHelper javaScriptHelper;

	public ProfilePageObject(WebDriver driver) {
		super(driver);
		btnHelper = new ButtonHelper(driver);
		textBoxHelper = new TextBoxHelper(driver);
		waitObj = new WaitHelper(driver, ObjectRepo.reader);
		javaScriptHelper = new JavaScriptHelper(driver);

	}

	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-toggle dropdown-icon']//img[@alt='setting']")
	public WebElement settingIcon;

	@FindBy(how = How.XPATH, using = "//a[@ng-click='viewProfile()']")
	public WebElement editProfile;

	@FindBy(how = How.XPATH, using = "//ul[@class='profile-follow-count']/li/a/span")
	public List<WebElement> allInformationName;
	
	@FindBy(how = How.XPATH, using = "//ul[@class='profile-follow-count']/li/a/div/p")
	public List<WebElement> allInformationCount;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Profile')]")
	public WebElement myProfile;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Dashboard')]")
	public WebElement dashBoard;

	@FindBy(how = How.XPATH, using = "//img[@class='feed-post-icon']")
	public WebElement postSection;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='vm.UploadedImgs']")
	public WebElement uploadPicture;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Post')]")
	public WebElement btnPost;

	@FindBy(how = How.XPATH, using = "//div[@class='post-comment-func post-like-btn']/a/p")
	public WebElement btnComment;
	
	@FindBy(how = How.XPATH, using = "//div[@class='user-reply-box']//textarea[@ng-model='vm.comment']")
	public WebElement txtAreaofComment;
	

	public void clickSettingIcon() throws Exception
	{
		waitObj.hardWait(10000);
		waitObj.elementExistAndVisibleelseSleep(settingIcon, 30, 3000);
		btnHelper.click(settingIcon);
		waitObj.hardWait(3000);
	}
	
	public void clickEdirProfile() throws Exception
	{
		waitObj.hardWait(4000);
		waitObj.elementExistAndVisibleelseSleep(editProfile, 30, 3000);
		waitObj.waitForElementVisible(editProfile);
		btnHelper.click(editProfile);	
	}
	
	public void allData() throws Exception
	{
		waitObj.waitForElementVisible(myProfile);
		waitObj.hardWait(4000);
		System.out.print(allInformationCount.size());
		for(int i = 0; i<allInformationCount.size(); i++) {
			
			String allInformationNames =  allInformationName.get(i).getText();
			System.out.print(allInformationNames + " ");

			String allInformationCounts =  allInformationCount.get(i).getText();
			System.out.print(allInformationCounts);
			System.out.print("\n");
	      }
	}
	
	public void clickDashboard() throws Exception
	{
		waitObj.hardWait(3000);
		btnHelper.click(dashBoard);	
	}
	
	public void postSection() throws Exception
	{
		waitObj.hardWait(3000);
		btnHelper.click(postSection);
		JavascriptExecutor js = (JavascriptExecutor) ObjectRepo.driver;
		js.executeScript("arguments[0].scrollIntoView();", postSection);
	}
	
	public void clickOnPhotoandSend() throws Exception
	{
		waitObj.hardWait(5000);
	    uploadPicture.sendKeys("D://test50.jpg");
		waitObj.hardWait(4000);
		btnHelper.click(btnPost);
	}
	
	public void clickOnComment() throws Exception
	{
		waitObj.hardWait(5000);
		btnHelper.click(btnComment);
		waitObj.hardWait(2000);
	}
	
	public void postComment() throws Exception
	{
		waitObj.hardWait(3000);
		javaScriptHelper.sendJavascript(txtAreaofComment, "Comment for testing automation");
		waitObj.hardWait(10000);
	}

}
