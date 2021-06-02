package com.fashionette.pom;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

public class FashionettePageObject extends BaseMethods {

	/*
	 * FASHIONETTE PAGE OBJECT In this class we store locators for web elements and
	 * methods to interact with them. Ideally we would multiple page object classes
	 * for different functionalities. As our example is small enough we will do it all
	 * in this one.
	 */
	// LOCATORS
	// Main page
	static By acceptCookiesButton = By.xpath("//*[@id='uc-btn-accept-banner']");
	static By goMainPageLogo = By.xpath("//img[@class='img-background__img']");
	static By luxuryHighlightBanner = By.xpath("//img[@alt='Luxury Highlights']");

	// User
	static By logInIcon = By.xpath("//a[@title='Login']");
	static By loggedUserIcon = By.xpath("//a[@data-id='user account']");
	static By logout = By.xpath("//a[contains(text(),'Logout')]");
	static By emailTextBox = By.xpath("//input[@name='email']");
	static By passwordTextBox = By.xpath("//input[@name='password']");
	static By loginButton = By.xpath("//button[contains(text(),'Login')]/parent::div");
	static By ordersSection = By.xpath("//a[contains(text(),'Orders')]");
	static By personalDataSection = By.xpath("//a[contains(text(),'Personal data')]");
	static By customerInfoText = By.xpath("//div[contains(text(),'Mrs')]");
	static By editCustomerInfo = By.xpath("//div[contains(text(),'Edit')]");
	static By editCustomerFirstName = By.xpath("//input[@name='firstName']");
	static By editCustomerLastName = By.xpath("//input[@name='lastName']");
	static By saveCustomerInfo = By.xpath("//div[contains(text(),'Save')]");

	// Search
	static By searchBox = By.xpath("//span[contains(text(),'What are you looking for?')]");
	static By searchInput = By.xpath("//input[@class='search-overlay__input']");
	static By searchResult = By.xpath("//div[@itemprop='potentialAction']/input[@name='search']");
	static By firstProductImage = By.xpath("//img[@class='loaded'][1]");

	// Cart
	static By itemPrice = By.xpath("//div[contains(text(),'£')][2]");
	static By addToCartButton = By.xpath("//div[@class='btn btn--bigger-icon preventspinner btn-default']");
	static By cartTotalPrice = By.xpath("//td[@id='cart__total']");
	static By oneItemOnCartBanner = By.xpath("//div[@class='container']/descendant::div[contains(text(),'1 Product')]");
	static By removeItem = By.xpath("//form[@class='cart-item--remove']/descendant::i");
	static By putBackRemoved = By.xpath("//button[contains(text(),'put back the last removed product')]");
	static By redeemVoucher = By.xpath("//a[contains(text(),'redeem')]");
	static By voucherTextBox = By.xpath("//input[@name='voucherCode']");
	static By applyVoucherButton = By.xpath("//button[@type='submit']/parent::div");
	static By notValidVoucherText = By.xpath("//div[contains(text(),'is not valid')]");

	// PAGE METHODS
	// MAIN PAGE
	// ---------------------------------------------------------------------------------------------
	// Accept cookies banner
	public static void acceptCookies() {
		clickVisible(acceptCookiesButton);
		checkVisible(searchBox);
	}

	// Clicks on top logo for going to main page
	public static void goToMainPage() {
		clickVisible(goMainPageLogo);
		checkVisible(luxuryHighlightBanner);
	}

	// Clicks on luxury highlights banner
	public static void clickLuxuryHighlights() {
		clickVisible(luxuryHighlightBanner);
	}

	// USER
	// --------------------------------------------------------------------------------------------------
	// Click user icon for login in
	public static void clickLogIcon() {
		clickVisible(logInIcon);
		checkVisible(loginButton);
	}

	// Click user icon for opening account details
	public static void clickLoggedIcon() {
		clickVisible(loggedUserIcon);
		checkVisible(logout);
	}

	// Click on logout from user details view
	public static void logout() {
		clickVisible(logout);
		checkVisible(goMainPageLogo);
	}

	// Send credentials and login from login view
	public static void login(String username, String password) {
		sendKeys(username, emailTextBox);
		sendKeys(password, passwordTextBox);
		clickVisible(loginButton);
		checkVisible(ordersSection);
	}

	// Clicks on personal data section of user details
	public static void clickPersonalData() {
		clickVisible(personalDataSection);
		checkVisible(editCustomerInfo);
	}

	// Clicks edit button at customer information personal data section of user
	// details
	public static void clickEditCustomerInfo() {
		clickVisible(editCustomerInfo);
		checkVisible(editCustomerFirstName);
		checkVisible(editCustomerLastName);
	}

	// Edit both first name and last name in edit customer info view and returns
	// generated names
	public static String editCustomerNames() {
		String firstName = BaseMethods.randomString();
		String lastName = BaseMethods.randomString();
		BaseMethods.clear(editCustomerFirstName);
		sendKeys(firstName, editCustomerFirstName);
		BaseMethods.clear(editCustomerLastName);
		sendKeys(lastName, editCustomerLastName);
		clickVisible(saveCustomerInfo);
		return firstName + " " + lastName;
	}

	// Gets Customer details as String
	public static String getCustomerInfoText() {
		return getText(customerInfoText);
	}

	// SEARCH
	// --------------------------------------------------------------------------------------------
	// Search products for given string
	public static void searchProduct(String chain) {
		clickVisible(searchBox);
		sendKeys(chain, searchInput);
		submit(searchInput);
		// Check searched string equals search result
		assertTrue(
				"Searched brand '" + chain + "' does not match category' "
						+ BaseMethods.getAttribute("Value", searchResult).toLowerCase() + "'",
				chain.equals(BaseMethods.getAttribute("Value", searchResult).toLowerCase()));
		checkVisible(firstProductImage);
	}

	// Select first available product and returns it price as String
	public static void selectProduct() {
		clickVisible(firstProductImage);
		checkVisible(addToCartButton);
	}

	// CART
	// ----------------------------------------------------------------------------------------------
	// Adds product to cart from product view
	public static String addToCart() {
		String result = BaseMethods.getText(itemPrice);
		clickVisible(addToCartButton);
		return result;
	}

	// Checks cart contains 1 item
	public static void checkCartItems() {
		checkVisible(oneItemOnCartBanner);
	}

	// Clicks on cart banner and opens current cart
	// Returns total price of cart as String
	public static String goToCart() {
		clickVisible(oneItemOnCartBanner);
		return getCartPrice();
	}

	// Returns actual total cart price
	public static String getCartPrice() {
		checkVisible(removeItem);
		return BaseMethods.getText(cartTotalPrice);
	}

	// Remove first item from cart view
	public static void removeItem() {
		clickVisible(removeItem);
		checkVisible(putBackRemoved);
	}

	// Click on redeem voucher button
	public static void clickRedeemVoucher() {
		clickVisible(redeemVoucher);
	}

	// Apply desired voucher
	public static void applyVoucher(String voucher) {
		sendKeys(voucher, voucherTextBox);
		clickVisible(applyVoucherButton);
	}

	// Check if invalid voucher message is displayed
	public static void checkInvalidVoucherMessage() {
		checkVisible(notValidVoucherText);
	}

}
