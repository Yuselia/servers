package com.servers.test.tests;

import com.servers.test.TestBase;
import com.servers.test.User;
import com.servers.test.pages.MainPage;
import com.servers.test.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginTest extends TestBase {
  private static LoginPage loginPage;
  private static MainPage mainPage;
  private static final User user = TestBase.user;
  private static final String errorMessage = "Incorrect email or password";

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
    mainPage = login(loginPage, user);
    logout(mainPage);
  }

  @Test(description = "Негативный тест логина")
  public void loginNTest() {
    loginPage.inputLogin(TestBase.user.getLogin() + "1")
            .inputPassword(TestBase.user.getPassword() + "1")
            .incorrectLoginButtonClick();
    assertThat("Корректное сообщение об ошибке", loginPage.getError(), is(errorMessage));
  }
}
