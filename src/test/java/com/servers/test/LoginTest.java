package com.servers.test;

import com.servers.test.pages.AccountInfoPage;
import com.servers.test.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
  //private static final String loginUrl = ConfigProperties.getProperty("main_url") + "/login";
  private static final String login = "yuselia+ask_ev@yandex.ru";
  private static final String password = "TestPassword!@#$";
  public static LoginPage loginPage;
  public static AccountInfoPage accountInfoPage;
  public static WebDriver driver;

  @BeforeClass
  public static void setup() {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.get(ConfigProperties.getProperty("login_url"));
    loginPage = new LoginPage(driver);
  }

  @AfterClass
  public static void tearDown() {
    driver.quit();
  }

  @Test(description = "Успешный логин")
  public void loginTest() {
    accountInfoPage = loginPage.declineCookiesIfNeed()
      .inputLogin(login)
      .inputPassword(password)
      .loginButtonClick();
    accountInfoPage.inputFirstName("test");
  }
}
