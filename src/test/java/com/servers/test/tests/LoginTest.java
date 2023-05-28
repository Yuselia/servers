package com.servers.test.tests;

import com.servers.test.TestBase;
import com.servers.test.pages.AccountInfoPage;
import com.servers.test.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
  public static LoginPage loginPage;

  @BeforeMethod
  public void setUp() {
    super.setUp();
    loginPage = new LoginPage(driver, wait);
    loginPage.declineCookiesIfNeed();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    super.tearDown();
  }

  @Test(description = "Успешный логин. Логаут")
  public void loginTest() {
    AccountInfoPage accountInfoPage = login(loginPage);
    accountInfoPage.closeModalWindowButton();
    logout(accountInfoPage);
  }

  /*@Test(description = "Unsuccessful login")
  public void loginNTest() {
    accountInfoPage = loginPage.inputLogin(login + "1")
            .inputPassword(password)
            .loginButtonClick();
    accountInfoPage.inputFirstName("test");
  } */
}
