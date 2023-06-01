package com.servers.test;

import com.servers.test.pages.LoginPage;
import com.servers.test.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestBase {
  public static final String main_url = ConfigProperties.getProperty("main_url");
  protected static final int timeout = Integer.valueOf(ConfigProperties.getProperty("timeout_seconds"));
  protected static WebDriver driver;
  protected static WebDriverWait wait;
  protected static String login_url = main_url + "/login";
  protected static User user = new User("yuselia+ask_ev@yandex.ru", "TestPassword!@#$");

  protected void setUp() {
    System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver");
    ChromeOptions chromeOptions = new ChromeOptions();
    //driver = new FirefoxDriver();
    driver = new ChromeDriver(chromeOptions);
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
