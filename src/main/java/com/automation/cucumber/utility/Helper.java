package com.automation.cucumber.utility;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.cucumber.settings.ObjectRepo;
import com.google.common.base.Function;

public class Helper {
	
	
	public WebDriverWait wait = new WebDriverWait(ObjectRepo.driver, 10);

	public WebElement element = null;

	public void browser_quit() {
		ObjectRepo.driver.quit();
	}

	// This function will get values from Clipboard.
	
	public String getClipboardContents(WebElement xPath) throws Exception {
		String result=null;
		xPath.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		xPath.sendKeys(Keys.chord(Keys.CONTROL, "c"));
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		
		try {
			 result = (String) clipboard.getData(DataFlavor.stringFlavor);
		}
		catch(Exception e){
			result = null;
		}
		return result;
	}

	public String ColorCode(WebElement element, String ColorCodeHex) {
		String color = element.getCssValue("background-color");
		System.out.println(color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return hex;
	}

	public String randomNumberGeneration() {
		Random rg = new Random();
		int randomInt = rg.nextInt(1000000)+1;
		System.out.println("Generated : " + randomInt);
		return  Integer.toString(randomInt);
	}

	public String FontColorCode(WebElement element, String ColorCodeHex) {
		String color = element.getCssValue("color");
		System.out.println("font color: " + color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return hex;
	}

	public String BtnColor(WebElement element) {
		String color = element.getCssValue("background-color");
		System.out.println(color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return hex;
	}

	public String FontColor(WebElement element) {
		String color = element.getCssValue("color");
		System.out.println("font color: " + color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return hex;
	}
	
	public String getToolTipText(WebElement element) throws Exception {
		Actions ToolTip = new Actions(ObjectRepo.driver);
	    Thread.sleep(2000);
	    ToolTip.clickAndHold(element).perform();
	    String ToolTipText = element.getAttribute("title");
	    Thread.sleep(2000);
	    System.out.println("Tooltip value is: " + ToolTipText);
		return ToolTipText;
	}

	public String FontColorCodeHex(WebElement element) {
		String color = element.getCssValue("color");
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);

		return hex;
	}

	public void scroll_down(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	public void scroll_element(String Element_location, WebDriver browser) {
		WebElement element = browser.findElement(By.xpath(Element_location));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", element);
	}

	public void scroll_up(WebDriver browser) {
		JavascriptExecutor jse = (JavascriptExecutor) browser;
		jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}


	public void FluentWaitClickAction(final WebElement InputElement, WebDriver driver) throws Exception {
		FluentWait<WebDriver> waitNew = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		WebElement foo = waitNew.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement foo = InputElement;

				wait.until(ExpectedConditions.visibilityOfAllElements(InputElement));
				wait.until(ExpectedConditions.elementToBeClickable(InputElement)).click();
				return foo;
			}
		});
	}

	public WebElement FluentWaitPage(final WebElement InputElement, WebDriver driver) throws Exception {
		FluentWait<WebDriver> waitNew = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(7)).ignoring(NoSuchElementException.class);

		WebElement foo = waitNew.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return element = InputElement;
			}
		});
		return element;
	}

	public void insertValueInDisableTxtBox(WebElement WebElement, String Value) throws Exception {
		WebElement textbox = WebElement;
		((JavascriptExecutor) ObjectRepo.driver).executeScript("arguments[0].placeholder='" + Value + "'", textbox);
	}

	public String currentSystemDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}
	
	public String NextDayFromCurrentDay() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		now = now.plusDays(1);
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}

	public String weekDateOfSunday() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		LocalDate sunday = today.with(previousOrSame(SUNDAY));
		System.out.println("Today: " + formatter.format(today));
		System.out.println("Sunday of this Week is on: " + formatter.format(sunday));
		String weekSunday = formatter.format(sunday);
		return weekSunday;
	}

	public String weekDateOfSaturday() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		LocalDate saturday = today.with(nextOrSame(SATURDAY));
		System.out.println("Today: " + formatter.format(today));
		System.out.println("Saturday of this Week is on: " + formatter.format(saturday));
		String weekSaturday = formatter.format(saturday);
		return weekSaturday;
	}

	public String thisMonthStartDate() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		String thisMonthStartDate = formatter.format(today.withDayOfMonth(1));
		System.out.println("Start Date of this Month is: " + thisMonthStartDate);
		return thisMonthStartDate;
	}

	public String thisMonthEndDate() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		String thisMonthEndDate = formatter.format(today.withDayOfMonth(today.lengthOfMonth()));
		System.out.println("End Date of this Month: " + thisMonthEndDate);
		return thisMonthEndDate;
	}

	public void javascriptExecutorClick(WebElement element) 
	{
		JavascriptExecutor executor = (JavascriptExecutor) ObjectRepo.driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void SelectDrpDwnValueByText(WebElement elementInput, String valueInput) throws Exception {
		Select Drpdwn = new Select(elementInput);
		Drpdwn.selectByVisibleText(valueInput);
		Thread.sleep(1000);
	}

	public void SelectDrpDwnValueByIndex(WebElement inputElement, int indexNo) throws Exception {
		Select dateDrpdwn = new Select(inputElement);
		dateDrpdwn.selectByIndex(indexNo);
		Thread.sleep(1000);
	}

	public String getDrpDwnFirstSelectedOptn(WebElement element) throws Exception {
		Select dateDrpdwn = new Select(element);
		WebElement option = dateDrpdwn.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println(defaultItem);
		Thread.sleep(1000);
		return defaultItem;
	}

	public Integer randomNum() throws Exception {
		Random random = new Random();
		int ranNum = random.nextInt(100000) + 1;
		return ranNum;

	}

	public String getParenthesesContent(String str) {
		return str.substring(str.indexOf('(') + 1, str.indexOf(')'));
	}

	public String integerToString(int arg) {
		String str = new Integer(arg).toString();
		System.out.println("String str4 = " + str);
		return str;
	}

}
