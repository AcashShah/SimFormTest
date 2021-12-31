package com.automation.cucumber.helper.Wait;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.cucumber.helper.Generic.GenericHelper;
import com.automation.cucumber.interfaces.IconfigReader;
import com.automation.cucumber.settings.ObjectRepo;
import com.google.common.base.Function;

public class WaitHelper extends GenericHelper {
	
	
	private WebDriver driver;
	private IconfigReader reader;
	private GenericHelper geneHelpObj; 

	public WaitHelper(WebDriver driver,IconfigReader reader) {
		super(driver);
		this.driver = driver;
		this.reader = reader;
		geneHelpObj = new GenericHelper(driver); 

	}
	public void waitForElementVisible(WebElement locator) {
		elementExistAndVisible(locator, ObjectRepo.reader.getTimeOutInSeconds(),ObjectRepo.reader.getPollingEveryInMiliSec());
	}
	public void invisibilityOfElementLocated(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))));
	}
	public void waitForElementClickable(WebElement locator) {
		waitForElementClickable(locator, ObjectRepo.reader.getTimeOutInSeconds(),ObjectRepo.reader.getPollingEveryInMiliSec());
	}
	private WebDriverWait getWait(int timeOutInSeconds,int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}


	public void setImplicitWait(long timeout,TimeUnit unit) {
		driver
		.manage()
		.timeouts()
		.implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	public void ImplicitWait(int timeout) {
		driver
		.manage()
		.timeouts()
		.implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public void waitForElementVisible(WebElement locator,int timeOutInSeconds,int pollingEveryInMiliSec) {
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator)))));
	}
	public void waitForElementClickable(WebElement locator,int timeOutInSeconds,int pollingEveryInMiliSec) {
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator)))));
	}
	public void hardWait(int timeOutInMiliSec) throws InterruptedException {
		Thread.sleep(timeOutInMiliSec);
	}

	public WebElement handleStaleElement(WebElement locator,int retryCount,int delayInMiliSeconds) throws InterruptedException {
		WebElement element = null;

		while (retryCount >= 0) {
			try {
				element = driver.findElement(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator)));
				return element;
			} catch (StaleElementReferenceException e) {
				hardWait(delayInMiliSeconds);
				retryCount--;
			}
		}
		throw new StaleElementReferenceException("Element cannot be recovered");
	}

	public void elementExits(WebElement locator,int timeOutInSeconds,int pollingEveryInMiliSec) {
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(elementLocatedBy(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))));
		setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	}

	public void elementExistAndVisible(WebElement locator,int timeOutInSeconds,int pollingEveryInMiliSec) {
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(elementLocatedBy(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(geneHelpObj.locatorParser(geneHelpObj.getLocatorFromWebElement(locator))));

	}

	public void waitForIframe(By locator,int timeOutInSeconds,int pollingEveryInMiliSec) {
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		driver.switchTo().defaultContent();
		setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	}
	
	public void waitForElementVisiblebasic(By locator,int timeOutInSeconds,int pollingEveryInMiliSec) {
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void elementExistAndVisibleelseSleep(WebElement locator,int timeOutInSeconds,int pollingEveryInMiliSec) throws Exception {
		try {
			elementExistAndVisible(locator,timeOutInSeconds,pollingEveryInMiliSec);
		}
		catch (Exception e) {
			hardWait(pollingEveryInMiliSec);
		}
	}
	
	public void waitForElementClickableelseSleep(WebElement locator,int timeOutInSeconds,int pollingEveryInMiliSec) throws Exception {
		try {
			waitForElementClickable(locator,timeOutInSeconds,pollingEveryInMiliSec);
		}
		catch (Exception e) {
			hardWait(pollingEveryInMiliSec);
		}
	}
	
	public void elementExistAndVisiblebasicelseSleep(By locator,int timeOutInSeconds,int pollingEveryInMiliSec) throws Exception {
		try {
			waitForElementVisiblebasic(locator,timeOutInSeconds,pollingEveryInMiliSec);
		}
		catch (Exception e) {
			hardWait(pollingEveryInMiliSec);
		}
	}

	private Function<WebDriver, Boolean> elementLocatedBy(final By locator){
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElements(locator).size() >= 1;
			}
		};
	}

}
