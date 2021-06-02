package com.fashionette.pom;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseMethods {

	/*
	 * BASE METHODS This class manages webDriver instance by provinding general
	 * methods to interact with web elements. For simplicity we use it also for
	 * storing constant data for the test and for store general utility functions.
	 */

	private static WebDriver driver;

	// CONSTANTS
	public final static String user = "QA@fashionette.de";
	public final static String password = "!8Ntr*BM@!#G3VH";
	public final static String search = "tommy hilfiger";
	public final static String invalidVoucher = "qachallenge";
	public final static String validVoucher = "luxury50";

	// BASE METHODS
	// Method for opening webDriver at Fashionette main page
	public static void loadMainPage() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.fashionette.co.uk/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Method for closing WebDriver
	public static void quit() {
		driver.quit();
	}

	// Method for refreshing WebDriver
	public static void refresh() {
		driver.navigate().refresh();
	}

	// Checks visibility of given objects
	public static void checkVisible(By locator) {
		WebElement element = driver.findElement(locator);
		// Explicit wait for visibility of element
		WebDriverWait waitDriver = new WebDriverWait(driver, 10);
		waitDriver.until(ExpectedConditions.visibilityOf(element));
		assertTrue("Element located by '" + locator.toString() + "' is not displayed", element.isDisplayed());
	}

	// Click a visible object
	public static void clickVisible(By locator) {
		checkVisible(locator);
		WebElement element = driver.findElement(locator);
		// Explicit wait for element to be clickable
		WebDriverWait waitDriver = new WebDriverWait(driver, 3);
		waitDriver.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// Get text of a visible object
	public static String getText(By locator) {
		checkVisible(locator);
		WebElement element = driver.findElement(locator);
		return element.getText();
	}

	// Get attribute passed as String of a visible object
	public static String getAttribute(String attribute, By locator) {
		checkVisible(locator);
		WebElement element = driver.findElement(locator);
		return element.getAttribute(attribute);
	}

	// Send given string chain to object identified by locator
	public static void sendKeys(String chain, By locator) {
		checkVisible(locator);
		WebElement element = driver.findElement(locator);
		element.sendKeys(chain);
	}

	// Remove form content
	public static void clear(By locator) {
		checkVisible(locator);
		WebElement element = driver.findElement(locator);
		element.clear();
	}

	// Submit data for element selected by locator
	public static void submit(By locator) {
		checkVisible(locator);
		WebElement element = driver.findElement(locator);
		element.submit();
	}

	// UTILITY METHODS
	// Function that generates a 5 length random string
	public static String randomString() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	// Get date as String
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
