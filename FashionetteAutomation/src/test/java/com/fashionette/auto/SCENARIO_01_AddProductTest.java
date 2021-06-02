package com.fashionette.auto;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.fashionette.pom.BaseMethods;
import com.fashionette.pom.FashionettePageObject;

public class SCENARIO_01_AddProductTest {

	@Before
	public void setUp() throws IOException {
		// Initialization of Chrome driver at Fashionette page
		BaseMethods.loadMainPage();

		// Accept Cookies
		FashionettePageObject.acceptCookies();
	}

	/* SCENARIO 01
	 * Test steps: 
	 * 1- Search products 
	 * 2- Select first available product 
	 * 3- Save price for later check and add item to cart 
	 * 4- Login with given user and check cart remains 
	 * 5- Go to cart and save total price of it 
	 * 6- Compare price of item with total cart 
	 * 7- Remove selected item (we leave user on the same status we take it) 
	 */
	@Test
	public void testAddProduct() {

		// Search and Select a product on Fashionette
		FashionettePageObject.searchProduct(BaseMethods.search);
		FashionettePageObject.selectProduct();

		// Click on Add to cart button and check product is correctly added
		String price = FashionettePageObject.addToCart();
		FashionettePageObject.checkCartItems();

		// Login to the system
		FashionettePageObject.clickLogIcon();
		FashionettePageObject.login(BaseMethods.user, BaseMethods.password);

		// Check that item still appear on CartBanner
		FashionettePageObject.checkCartItems();

		// Click on CartBanner and save cart total price
		String cartTotal = FashionettePageObject.goToCart();

		// Price of product have to be the same as total cart price
		assertTrue("Item price '" + price + "' does not match cart total '" + cartTotal + "'", price.equals(cartTotal));

		// Remove item and for future test runs and go to main page
		FashionettePageObject.removeItem();

	}

	@After
	public void tearDown() {		
		// Finish and close test
		BaseMethods.quit();
	}

}
