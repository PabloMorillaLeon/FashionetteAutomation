package com.fashionette.auto;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.fashionette.pom.BaseMethods;
import com.fashionette.pom.FashionettePageObject;

public class SCENARIO_03_ApplyVoucherTest {

	@Before
	public void setUp() throws IOException {
		// Initialization of Chrome driver at Fashionette page
		BaseMethods.loadMainPage();

		// Accept Cookies
		FashionettePageObject.acceptCookies();
	}

	/* SCENARIO 03
	 * Test steps: 
	 * 1- Search for a luxury highlight product and select it
	 * 2- Add product to cart and go to cart
	 * 3- Try to redeem not working code "qachallenge" and check is not working
	 * 4- Redeem working code "luxury50" and check discount
	 * 5- Remove selected item (we leave user on the same status we take it) 
	 */
	@Test
	public void testChangeUsername() throws InterruptedException {

		// Search and Select a product on Fashionette
		FashionettePageObject.clickLuxuryHighlights();
		FashionettePageObject.selectProduct();

		// Click on Add to cart button and check product is correctly added
		FashionettePageObject.addToCart();

		// Click on CartBanner and save cart total price
		String cartPrice = FashionettePageObject.goToCart();
		cartPrice = cartPrice.substring(1).replace(",", ""); // Remove "£" and "," cast to Double
		Double initialCartPrice = Double.parseDouble(cartPrice);

		// Click on redeem voucher button
		FashionettePageObject.clickRedeemVoucher();

		// Try to apply not valid voucher and check the invalid voucher message
		FashionettePageObject.applyVoucher(BaseMethods.invalidVoucher);
		FashionettePageObject.checkInvalidVoucherMessage();

		// Try to apply valid voucher
		FashionettePageObject.applyVoucher(BaseMethods.validVoucher);

		// We refresh page so we get actual price by going to main page and back to cart
		FashionettePageObject.goToMainPage();
		cartPrice = FashionettePageObject.goToCart().substring(1).replace(",", ""); // Remove "£" and "," cast to Double
		Double actualCartPrice = Double.parseDouble(cartPrice);

		// Check price reduction
		assertTrue("Discount is not correctly applied", initialCartPrice - 50 == actualCartPrice);

		// Remove item and for future test runs and go to main page
		FashionettePageObject.removeItem();

	}

	@After
	public void tearDown() {
		// Finish and close test
		BaseMethods.quit();
	}

}
