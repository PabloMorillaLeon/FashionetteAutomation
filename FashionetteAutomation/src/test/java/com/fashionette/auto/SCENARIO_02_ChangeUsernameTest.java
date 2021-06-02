package com.fashionette.auto;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.fashionette.pom.BaseMethods;
import com.fashionette.pom.FashionettePageObject;

public class SCENARIO_02_ChangeUsernameTest {

	@Before
	public void setUp() throws IOException {
		// Initialization of Chrome driver at Fashionette page
		BaseMethods.loadMainPage();

		// Accept Cookies
		FashionettePageObject.acceptCookies();
	}

	/* SCENARIO 02
	 * Test steps: 
	 * 1- Login with given user 
	 * 2- Go to personal data section in user view
	 * 3- Click on edit at customer information section
	 * 4- Insert two new randomly generated name and surname and save them
	 * 5- Check the new names are correctly saved 
	 */
	@Test
	public void testChangeUsername() {

		// Login to the system
		FashionettePageObject.clickLogIcon();
		FashionettePageObject.login(BaseMethods.user, BaseMethods.password);

		// Click on Personal Data
		FashionettePageObject.clickPersonalData();

		// Click on Edit Customer Info
		FashionettePageObject.clickEditCustomerInfo();

		// Insert two random names and store them
		String newName = FashionettePageObject.editCustomerNames();

		// We check that our new name appears at customer information section
		assertTrue(
				"New name '" + newName + "' is not contained in '" + FashionettePageObject.getCustomerInfoText() + "'",
				FashionettePageObject.getCustomerInfoText().contains(newName));

	}

	@After
	public void tearDown() {
		// Finish and close test
		BaseMethods.quit();
	}

}
