package com.servers.test;

import com.servers.test.pages.MainPage;
import com.servers.test.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestBase {
  protected static final String main_url = ConfigProperties.getProperty("main_url");
  private static final String login_url = main_url + "/login";
  private static final int timeout = Integer.valueOf(ConfigProperties.getProperty("timeout_seconds"));
  public static WebDriver driver;
  public static WebDriverWait wait;
  public static User user = new User("yuselia+ask_ev@yandex.ru", "TestPassword!@#$");

  protected void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration. ofSeconds(timeout));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    driver.get(login_url);
    wait.until(titleIs("Servers.com – Customer Portal"));
  }

  protected void tearDown() {
    driver.quit();
  }

  protected MainPage login(LoginPage loginPage, User user) {
    MainPage mainPage = loginPage.inputLogin(user.getLogin())
            .inputPassword(user.getPassword())
            .loginButtonClick();
    mainPage.checkAccountName(user.getLogin());
    return mainPage;
  }

  protected LoginPage logout(MainPage mainPage) {
    return mainPage.accountButtonClick()
            .logoutButtonClick();
  }
}
